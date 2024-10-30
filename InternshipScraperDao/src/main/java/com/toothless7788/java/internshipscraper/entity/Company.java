package com.toothless7788.java.internshipscraper.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Company")    // Just in case: it will be named according to @Entity by default
public class Company {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="company_id")
	private long id;
	@Column(length=128, nullable=false, name="company_name")
	private String name;
	@Enumerated(EnumType.STRING)
	private Field field;
	@Enumerated(EnumType.STRING)
	private CompanyCategory category;
	@Column(length=128)
	private String address;
	private int dateOfIncorporation;
	@Column(length=255)
	private String websiteLink;
	@Column(length=64)
	private String countryOfOrigin;
	@Column(length=128, name="company_description")
	private String description;
	
	@OneToMany(mappedBy="company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)    // Name of variable in Company
	private List<Application> applications;
	
	protected Company() {
		// For JPA
	}
	
	public Company(String name, Field field, CompanyCategory category, String address, int dateOfIncorporation, String websiteLink, String countryOfOrigin, String description) {
		this.name = name;
		this.field = field;
		this.category = category;
		this.address = address;
		this.dateOfIncorporation = dateOfIncorporation;
		this.websiteLink = websiteLink;
		this.countryOfOrigin = countryOfOrigin;
		this.description = description;
	}
	
	/**
	 * For copying the instance to be deleted
	 * 
	 * @param c The original company instance to be deleted
	 */
	public Company(Company c) {
		this.id = c.getId();
		this.name = c.getName();
		this.field = c.getField();
		this.category = c.getCategory();
		this.address = c.getAddress();
		this.dateOfIncorporation = c.getDateOfIncorporation();
		this.websiteLink = c.getWebsiteLink();
		this.countryOfOrigin = c.getCountryOfOrigin();
		this.description = c.getDescription();
		
		this.applications = c.getApplications();
	}

	// ===================================================
		// Getters and setters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public CompanyCategory getCategory() {
		return category;
	}

	public void setCategory(CompanyCategory category) {
		this.category = category;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getDateOfIncorporation() {
		return dateOfIncorporation;
	}

	public void setDateOfIncorporation(int dateOfIncorporation) {
		this.dateOfIncorporation = dateOfIncorporation;
	}

	public String getWebsiteLink() {
		return websiteLink;
	}

	public void setWebsiteLink(String websiteLink) {
		this.websiteLink = websiteLink;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonIgnore
	public List<Application> getApplications() {
		return this.applications;
	}
	
	// ===========================================
		@Override
		public boolean equals(Object o) {
			if(!(o instanceof Company) || o == null) {
				return false;
			} else {
				return ((Company)o).getId() == getId();
			}
		}
		
		@Override
		/**
		 * TODO Not sure about if it is safe to cast from long to int
		 */
		public int hashCode() {
			int hash = 7;
		    hash = 31 * hash + (int) getId();
		    return hash;
		}
	
	@Override
	public String toString() {
		StringBuilder text = new StringBuilder();
		text.append("{");
		
		text.append("\n    ");
		text.append("company_id: ");
		text.append(getId());
		text.append(", ");
		
		text.append("\n    ");
		text.append("company_name: ");
		text.append(getName());
		text.append(", ");
		
		text.append("\n    ");
		text.append("field: ");
		text.append(getField());
		text.append(", ");
		
		text.append("\n    ");
		text.append("category: ");
		text.append(getCategory());
		text.append(", ");
		
		text.append("\n    ");
		text.append("address: ");
		text.append(getAddress());
		text.append(", ");
		
		text.append("\n    ");
		text.append("date_of_incorporation: ");
		text.append(getDateOfIncorporation());
		text.append(", ");
		
		text.append("\n    ");
		text.append("website_link: ");
		text.append(getWebsiteLink());
		text.append(", ");
		
		text.append("\n    ");
		text.append("country_of_origin: ");
		text.append(getCountryOfOrigin());
		text.append(", ");
		
		text.append("\n    ");
		text.append("company_description: ");
		text.append(getDescription());
		
		text.append("\n}");
		
		return text.toString();
	}
}
