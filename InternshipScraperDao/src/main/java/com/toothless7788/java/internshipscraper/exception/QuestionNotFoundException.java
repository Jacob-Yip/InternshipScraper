package com.toothless7788.java.internshipscraper.exception;

public final class QuestionNotFoundException extends InstanceNotFoundException {

	/**
	 * Generated
	 */
	private static final long serialVersionUID = -997005783535718643L;

	public QuestionNotFoundException(long questionID) {
		super("Question not found: " + questionID);
	}
	
}
