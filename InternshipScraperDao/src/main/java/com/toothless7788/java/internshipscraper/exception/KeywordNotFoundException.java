package com.toothless7788.java.internshipscraper.exception;

import com.toothless7788.java.internshipscraper.entity.KeywordID;

public final class KeywordNotFoundException extends InstanceNotFoundException {

	/**
	 * Generated
	 */
	private static final long serialVersionUID = 9057117446034352668L;

	public KeywordNotFoundException(KeywordID keywordID) {
		super("Keyword not found: (" + keywordID.getKeyword() + ", " + keywordID.getApplicationID() + ")");
	}
	
}
