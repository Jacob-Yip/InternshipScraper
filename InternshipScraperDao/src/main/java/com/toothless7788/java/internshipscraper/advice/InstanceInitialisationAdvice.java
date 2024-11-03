package com.toothless7788.java.internshipscraper.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.toothless7788.java.internshipscraper.exception.InstanceInitialisationException;

@RestControllerAdvice
public class InstanceInitialisationAdvice {
	@ExceptionHandler(InstanceInitialisationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String instanceInitilisationHandler(InstanceInitialisationException ex) {
		return ex.getMessage();
	}
}
