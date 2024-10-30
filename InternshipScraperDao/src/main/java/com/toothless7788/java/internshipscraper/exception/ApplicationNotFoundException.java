package com.toothless7788.java.internshipscraper.exception;

public final class ApplicationNotFoundException extends InstanceNotFoundException {
	/**
	 * Generated
	 */
	private static final long serialVersionUID = 2022084895359610433L;

	public ApplicationNotFoundException(long applicationID) {
		super("Application not found: " + applicationID);
	}
	
}
