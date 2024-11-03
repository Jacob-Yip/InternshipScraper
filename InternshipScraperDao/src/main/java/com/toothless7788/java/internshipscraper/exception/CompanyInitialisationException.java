package com.toothless7788.java.internshipscraper.exception;

import com.toothless7788.java.internshipscraper.entity.Company;

public final class CompanyInitialisationException extends InstanceInitialisationException {

	/**
	 * Generated
	 */
	private static final long serialVersionUID = 8466481157861997759L;

	public CompanyInitialisationException(Company instance) {
		super("Company", instance);
	}
	
	public CompanyInitialisationException(Company instance, Exception e) {
		super("Company", instance, e);
	}
	
}
