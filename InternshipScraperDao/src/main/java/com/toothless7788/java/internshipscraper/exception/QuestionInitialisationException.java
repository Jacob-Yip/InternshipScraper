package com.toothless7788.java.internshipscraper.exception;

import com.toothless7788.java.internshipscraper.entity.Question;

public final class QuestionInitialisationException extends InstanceInitialisationException {

	/**
	 * Generated
	 */
	private static final long serialVersionUID = -7306839542696950393L;

	public QuestionInitialisationException(Question instance) {
		super("Question", instance);
	}
	
	public QuestionInitialisationException(Question instance, Exception e) {
		super("Question", instance, e);
	}
	
}
