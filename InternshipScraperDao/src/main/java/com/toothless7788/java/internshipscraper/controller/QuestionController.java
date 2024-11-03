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

import com.toothless7788.java.internshipscraper.entity.Question;
import com.toothless7788.java.internshipscraper.exception.QuestionInitialisationException;
import com.toothless7788.java.internshipscraper.exception.QuestionNotFoundException;
import com.toothless7788.java.internshipscraper.service.QuestionService;

@RestController
@RequestMapping(value="question")
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
	
	@GetMapping("all")
	public CollectionModel<EntityModel<Question>> all() {
		List<EntityModel<Question>> questions = questionService.findAll().stream().map((Question question) ->
		EntityModel.of(
				question, 
				linkTo(methodOn(QuestionController.class).one(Long.valueOf(question.getId()))).withSelfRel(), 
				linkTo(methodOn(QuestionController.class).all()).withRel("questions")
		)
	).collect(Collectors.toList());
		
	return CollectionModel.of(questions, linkTo(methodOn(QuestionController.class).all()).withSelfRel());
	}
	
	@GetMapping("one/{id}")
	public EntityModel<Question> one(@PathVariable Long id) throws QuestionNotFoundException {
		Question question = questionService.findById(id);
		
		if(question == null) {
			throw new QuestionNotFoundException(id.longValue());
		}
		
		return EntityModel.of(
				question, 
				linkTo(methodOn(QuestionController.class).one(id)).withSelfRel(),    // Ask Spring HATEOAS to build a link to the one() in QuestionController and flag it as a link called "self" 
				linkTo(methodOn(QuestionController.class).all()).withRel("questions")    // Ask Spring HATEOAS to build a link to the all() in QuestionController and flag it as a link called "questions"
		);
	}
	
	@PostMapping("/")
	public EntityModel<Question> newQuestion(@RequestBody Question question) throws QuestionInitialisationException {
		try {
			Question targetQuestion = questionService.addQuestion(question);    // Will never be null
			
			Question addedQuestion = questionService.findById(targetQuestion.getId());
			
			return EntityModel.of(
					addedQuestion, 
					linkTo(methodOn(QuestionController.class).one(Long.valueOf(addedQuestion.getId()))).withSelfRel(), 
					linkTo(methodOn(QuestionController.class).all()).withRel("questions")
					);
		} catch(Exception e) {
			throw new QuestionInitialisationException(question, e);
		}
	}
	
	/**
	 * If an invalid id is passed, QuestionNotFoundExceptions will be thrown
	 * 
	 * @param id ID of the Question to be deleted
	 * @return The deleted entity if exists, else null
	 * @throws QuestionNotFoundException Thrown when an invalid question_id is inputted
	 */
	@DeleteMapping("one/{id}")
	public EntityModel<Question> removeQuestion(@PathVariable Long id) {
		Question targetQuestion = questionService.findById(id);
		
		if(targetQuestion == null) {
			throw new QuestionNotFoundException(id.longValue());
		}
		
		Question deletedQuestion = new Question(targetQuestion);
		
		questionService.deleteById(id);
		
		return EntityModel.of(
				deletedQuestion, 
				linkTo(methodOn(QuestionController.class).all()).withRel("questions")
		);
	}
}
