package com.toothless7788.java.internshipscraper.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.toothless7788.java.internshipscraper.exception.EmptyInstanceException;

@RestControllerAdvice
public class EmptyInstanceAdvice {
	
	@ExceptionHandler(EmptyInstanceException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String emptyInstanceHandler(EmptyInstanceException ex) {
		return ex.getMessage();
	}
}
