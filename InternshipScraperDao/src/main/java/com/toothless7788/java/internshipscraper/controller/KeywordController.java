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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.toothless7788.java.internshipscraper.entity.Keyword;
import com.toothless7788.java.internshipscraper.entity.KeywordID;
import com.toothless7788.java.internshipscraper.exception.EmptyKeywordIDException;
import com.toothless7788.java.internshipscraper.exception.KeywordNotFoundException;
import com.toothless7788.java.internshipscraper.service.KeywordService;

@RestController
@RequestMapping("/keyword")
public class KeywordController {
	@Autowired
	private KeywordService keywordService;
	private static final Logger log = LoggerFactory.getLogger(KeywordController.class);
	
	@GetMapping("all")
	public CollectionModel<EntityModel<Keyword>> all() {
		List<EntityModel<Keyword>> keywords = keywordService.findAll().stream().map((Keyword keyword) ->
		EntityModel.of(
				keyword, 
				linkTo(methodOn(KeywordController.class).one(Long.valueOf(keyword.getID().getApplicationID()), keyword.getID().getKeyword())).withSelfRel(), 
				linkTo(methodOn(KeywordController.class).all()).withRel("keywords")
		)
	).collect(Collectors.toList());
		
	return CollectionModel.of(keywords, linkTo(methodOn(KeywordController.class).all()).withSelfRel());
	}
	
	@GetMapping("one")
	public EntityModel<Keyword> one(@RequestParam Long id, @RequestParam String keyword) {
		if(id == null || keyword == null) {
			throw new EmptyKeywordIDException();
		}
		
		KeywordID targetIdentifier = new KeywordID(keyword, id.longValue());
		Keyword targetKeyword = keywordService.findByID(targetIdentifier);
		
		return EntityModel.of(
				targetKeyword, 
				linkTo(methodOn(KeywordController.class).one(id, keyword)).withSelfRel(),    // Ask Spring HATEOAS to build a link to the one() in KeywordController and flag it as a link called "self" 
				linkTo(methodOn(KeywordController.class).all()).withRel("keywords")    // Ask Spring HATEOAS to build a link to the all() in KeywordController and flag it as a link called "keywords"
		);
	}
	
	@PostMapping("/")
	public EntityModel<Keyword> newKeyword(@RequestBody Keyword keyword) {
		Keyword targetKeyword = keywordService.addKeyword(keyword.getID());    // Will never be null
		
		Keyword addedKeyword = keywordService.findByID(targetKeyword.getID());
		
		return EntityModel.of(
				addedKeyword, 
				linkTo(methodOn(KeywordController.class).one(Long.valueOf(addedKeyword.getID().getApplicationID()), addedKeyword.getID().getKeyword())).withSelfRel(), 
				linkTo(methodOn(KeywordController.class).all()).withRel("keywords")
		);
	}
	
	/**
	 * If an invalid id is passed, KeywordNotFoundExceptions will be thrown
	 * 
	 * @param id
	 * @param keyword
	 * @return
	 * @throws EmptyKeywordIDException
	 * @throws KeywordNotFoundException Thrown when an invalid keyword_id is inputted
	 */
	@DeleteMapping("one")
	public EntityModel<Keyword> removeKeyword(@RequestParam Long id, @RequestParam String keyword) throws EmptyKeywordIDException, KeywordNotFoundException {
		if(id == null || keyword == null) {
			throw new EmptyKeywordIDException();
		}
		
		KeywordID targetIdentifier = new KeywordID(keyword, id.longValue());
		Keyword targetKeyword = keywordService.findByID(targetIdentifier);
		
		if(targetKeyword == null) {
			throw new KeywordNotFoundException(targetIdentifier);
		}
		
		Keyword deletedKeyword = new Keyword(targetKeyword);
		
		keywordService.deleteKeyword(targetIdentifier);
		
		return EntityModel.of(
				deletedKeyword, 
				linkTo(methodOn(KeywordController.class).all()).withRel("keywords")
		);
	}
}
