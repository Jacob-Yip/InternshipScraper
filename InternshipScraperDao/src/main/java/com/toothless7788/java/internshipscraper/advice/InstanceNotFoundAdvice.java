package com.toothless7788.java.internshipscraper.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.toothless7788.java.internshipscraper.exception.InstanceNotFoundException;

@RestControllerAdvice
public class InstanceNotFoundAdvice {
	
	@ExceptionHandler(InstanceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String instanceNotFoundHandler(InstanceNotFoundException ex) {
		return ex.getMessage();
	}
}
