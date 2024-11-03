package com.toothless7788.java.internshipscraper.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toothless7788.java.internshipscraper.entity.Application;
import com.toothless7788.java.internshipscraper.exception.ApplicationInitialisationException;
import com.toothless7788.java.internshipscraper.exception.ApplicationNotFoundException;
import com.toothless7788.java.internshipscraper.service.ApplicationService;

@RestController
@RequestMapping(value="application")
public class ApplicationController {
	@Autowired
	private ApplicationService applicationService;
	private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);
	
	@GetMapping("all")
	public CollectionModel<EntityModel<Application>> all() {
		List<EntityModel<Application>> applications = applicationService.findAll().stream().map((Application application) ->
		EntityModel.of(
				application, 
				linkTo(methodOn(ApplicationController.class).one(Long.valueOf(application.getId()))).withSelfRel(), 
				linkTo(methodOn(ApplicationController.class).all()).withRel("applications")
		)
	).collect(Collectors.toList());
		
	return CollectionModel.of(applications, linkTo(methodOn(ApplicationController.class).all()).withSelfRel());
	}
	
	@GetMapping("one/{id}")
	public EntityModel<Application> one(@PathVariable Long id) throws ApplicationNotFoundException {
		Application application = applicationService.findById(id);
		
		if(application == null) {
			throw new ApplicationNotFoundException(id.longValue());
		}
		
		return EntityModel.of(
				application, 
				linkTo(methodOn(ApplicationController.class).one(id)).withSelfRel(),    // Ask Spring HATEOAS to build a link to the one() in ApplicationController and flag it as a link called "self" 
				linkTo(methodOn(ApplicationController.class).all()).withRel("applications")    // Ask Spring HATEOAS to build a link to the all() in ApplicationController and flag it as a link called "applications"
		);
	}
	
	@PostMapping("/")
	public EntityModel<Application> newApplication(@RequestBody Application application) throws ApplicationInitialisationException {
		try {
			Application targetApplication = applicationService.addApplication(application);    // Will never be null
			
			Application addedApplication = applicationService.findById(targetApplication.getId());
			
			return EntityModel.of(
					addedApplication, 
					linkTo(methodOn(ApplicationController.class).one(Long.valueOf(addedApplication.getId()))).withSelfRel(), 
					linkTo(methodOn(ApplicationController.class).all()).withRel("applications")
					);
		} catch(Exception e) {
			throw new ApplicationInitialisationException(application, e);
		}
	}
	
	/**
	 * If an invalid id is passed, ApplicationNotFoundExceptions will be thrown
	 * 
	 * @param id ID of the Application to be deleted
	 * @return The deleted entity if exists, else null
	 * @throws ApplicationNotFoundException Thrown when an invalid application_id is inputted
	 */
	@DeleteMapping("one/{id}")
	public EntityModel<Application> removeApplication(@PathVariable Long id) {
		Application targetApplication = applicationService.findById(id);
		
		if(targetApplication == null) {
			throw new ApplicationNotFoundException(id.longValue());
		}
		
		Application deletedApplication = new Application(targetApplication);
		
		applicationService.deleteById(id);
		
		return EntityModel.of(
				deletedApplication, 
				linkTo(methodOn(ApplicationController.class).all()).withRel("applications")
		);
	}
}
