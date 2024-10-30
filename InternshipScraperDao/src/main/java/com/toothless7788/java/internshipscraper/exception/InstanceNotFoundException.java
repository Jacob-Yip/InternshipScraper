package com.toothless7788.java.internshipscraper.exception;

import java.util.NoSuchElementException;

public class InstanceNotFoundException extends NoSuchElementException {

	/**
	 * Generated
	 */
	private static final long serialVersionUID = -4751788407606810134L;
	
	public InstanceNotFoundException(String msg) {
		super(msg);
	}
	
	public InstanceNotFoundException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
