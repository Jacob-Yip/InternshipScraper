package com.toothless7788.java.internshipscraper.exception;

public class InstanceInitialisationException extends IllegalArgumentException {

	/**
	 * Generated
	 */
	private static final long serialVersionUID = -340592777153916911L;
	
	public InstanceInitialisationException(String instanceName, Object instance) {
		super("Error in creating " + instanceName + " instance: " + instance.toString());
	}
	
	public InstanceInitialisationException(String instanceName, Object instance, Exception e) {
		super("Error in creating " + instanceName + " instance: " + instance.toString(), e);
	}
	
}
