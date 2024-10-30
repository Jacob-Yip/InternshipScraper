package com.toothless7788.java.internshipscraper.exception;

public final class CompanyNotFoundException extends InstanceNotFoundException {

	/**
	 * Generated
	 */
	private static final long serialVersionUID = 8149553455517593286L;

	public CompanyNotFoundException(long companyID) {
		super("Company Not Found: " + companyID);
	}
	
}
