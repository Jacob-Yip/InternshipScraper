package com.toothless7788.java.internshipscraper.exception;

import com.toothless7788.java.internshipscraper.entity.Keyword;

public final class KeywordInitialisationException extends InstanceInitialisationException {

	/**
	 * Generated
	 */
	private static final long serialVersionUID = 8897479169081209554L;

	public KeywordInitialisationException(Keyword instance) {
		super("Keyword", instance);
	}
	
	public KeywordInitialisationException(Keyword instance, Exception e) {
		super("Keyword", instance, e);
	}
	
}
