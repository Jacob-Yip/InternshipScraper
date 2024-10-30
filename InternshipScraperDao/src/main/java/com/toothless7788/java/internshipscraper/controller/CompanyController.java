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

import com.toothless7788.java.internshipscraper.entity.Company;
import com.toothless7788.java.internshipscraper.exception.CompanyNotFoundException;
import com.toothless7788.java.internshipscraper.service.CompanyService;

@RestController
@RequestMapping(value="company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	private static final Logger log = LoggerFactory.getLogger(CompanyController.class);
	
	@GetMapping("all")
	public CollectionModel<EntityModel<Company>> all() {
		List<EntityModel<Company>> companies = companyService.findAll().stream().map((Company company) ->
		EntityModel.of(
				company, 
				linkTo(methodOn(CompanyController.class).one(Long.valueOf(company.getId()))).withSelfRel(), 
				linkTo(methodOn(CompanyController.class).all()).withRel("companies")
		)
	).collect(Collectors.toList());
		
	return CollectionModel.of(companies, linkTo(methodOn(CompanyController.class).all()).withSelfRel());
	}
	
	@GetMapping("one/{id}")
	public EntityModel<Company> one(@PathVariable Long id) {
		Company company = companyService.findById(id);
		
		return EntityModel.of(
				company, 
				linkTo(methodOn(CompanyController.class).one(id)).withSelfRel(),    // Ask Spring HATEOAS to build a link to the one() in CompanyController and flag it as a link called "self" 
				linkTo(methodOn(CompanyController.class).all()).withRel("companies")    // Ask Spring HATEOAS to build a link to the all() in CompanyController and flag it as a link called "companies"
		);
	}
	
	@PostMapping("/")
	public EntityModel<Company> newCompany(@RequestBody Company company) {
		Company targetCompany = companyService.addCompany(company);    // Will never be null
		
		Company addedCompany = companyService.findById(targetCompany.getId());
		
		return EntityModel.of(
				addedCompany, 
				linkTo(methodOn(CompanyController.class).one(Long.valueOf(addedCompany.getId()))).withSelfRel(), 
				linkTo(methodOn(CompanyController.class).all()).withRel("companies")
		);
	}
	
	/**
	 * If an invalid id is passed, CompanyNotFoundExceptions will be thrown
	 * 
	 * @param id ID of the Company to be deleted
	 * @return The deleted entity if exists, else null
	 * @throws CompanyNotFoundException Thrown when an invalid company_id is inputted
	 */
	@DeleteMapping("one/{id}")
	public EntityModel<Company> removeCompany(@PathVariable Long id) {
		Company targetCompany = companyService.findById(id);
		
		if(targetCompany == null) {
			throw new CompanyNotFoundException(id.longValue());
		}
		
		Company deletedCompany = new Company(targetCompany);
		
		companyService.deleteById(id);
		
		return EntityModel.of(
				deletedCompany, 
				linkTo(methodOn(CompanyController.class).all()).withRel("companies")
		);
	}
}
