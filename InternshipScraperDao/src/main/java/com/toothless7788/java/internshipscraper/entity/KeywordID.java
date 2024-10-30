package com.toothless7788.java.internshipscraper.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class KeywordID implements Serializable {

	/**
	 * Generated
	 */
	private static final long serialVersionUID = -4798614726350196416L;
	@Column(length=64, nullable=false)
	private String keyword;
	@Column(nullable=false, name="application_id")
	private long applicationID;
	
	protected KeywordID() {
		// For JPA
	}
	
	public KeywordID(String keyword, long applicationID) {
		this.keyword = keyword;
		this.applicationID = applicationID;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public long getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(long applicationID) {
		this.applicationID = applicationID;
	}

	// ===========================================
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof KeywordID) || o == null) {
			return false;
		} else {
			return this.getKeyword() == ((KeywordID)o).getKeyword() && this.getApplicationID() == ((KeywordID)o).getApplicationID();
		}
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
	    hash = 31 * hash + (getKeyword() == null ? 0 : getKeyword().hashCode());
	    hash = 31 * hash + (int) getApplicationID();
	    return hash;
	}

	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		text.append("{");
		
		text.append("\n    ");
		text.append("keyword: ");
		text.append(getKeyword());
		text.append(", ");
		
		text.append("\n    ");
		text.append("application_id: ");
		text.append(getApplicationID());
		
		text.append("\n}");
		
		return text.toString();
	}
}
