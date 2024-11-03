package com.toothless7788.java.internshipscraper.exception;

import com.toothless7788.java.internshipscraper.entity.Application;

public final class ApplicationInitialisationException extends InstanceInitialisationException {

	/**
	 * Generated
	 */
	private static final long serialVersionUID = -8658581553871525643L;

	public ApplicationInitialisationException(Application instance) {
		super("Application", instance);
	}
	
	public ApplicationInitialisationException(Application instance, Exception e) {
		super("Application", instance, e);
	}
	
}
