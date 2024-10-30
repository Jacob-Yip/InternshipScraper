package com.toothless7788.java.internshipscraper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Keyword")    // Just in case: it will be named according to @Entity by default
public class Keyword {
	@EmbeddedId
	@Column(name="keyword_id")
	private KeywordID id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="application_id", referencedColumnName="application_id", nullable=false, insertable=false, updatable=false)
	private Application application;
	@Column(columnDefinition = "UNSIGNED INT", nullable=false)
	private int count;
	
	protected Keyword() {
		// For JPA
	}
	
	public Keyword(KeywordID id, int count) {
		this.id = id;
		this.count = count;
	}
	
	public Keyword(Keyword k) {
		this.id = k.getID();
		this.application = k.getApplication();
		this.count = k.getCount();
	}

	// ===================================================
	// Getters and setters
	
	public KeywordID getID() {
		return this.id;
	}

	public void setID(KeywordID id) {
		this.id = id;
	}
	
	public Application getApplication() {
		return this.application;
	}
	
	public void setApplication(Application application) {
		this.application = application;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	// ===========================================
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Keyword) || o == null) {
			return false;
		} else {
			return ((Keyword)o).getID() == this.getID();
		}
	}
	
	@Override
	/**
	 * TODO Not sure about if it is safe to cast from long to int
	 */
	public int hashCode() {
		int hash = 7;
	    hash = 31 * hash + (getID() == null ? 0 : getID().hashCode());
	    return hash;
	}
		
	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		text.append("{");
		
		text.append("\n    ");
		text.append("keywordID: ");
		text.append(getID());
		text.append(", ");
		
		text.append("\n    ");
		text.append("count: ");
		text.append(getCount());
		
		text.append("\n}");
		
		return text.toString();
	}
}
