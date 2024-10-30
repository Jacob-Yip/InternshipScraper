package com.toothless7788.java.internshipscraper.exception;

public class EmptyInstanceException extends IllegalArgumentException {

	/**
	 * Generated
	 */
	private static final long serialVersionUID = 5611489501922009010L;
	
	public EmptyInstanceException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public EmptyInstanceException(String msg) {
		super(msg);
	}
}
