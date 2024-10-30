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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Application")    // Just in case: it will be named according to @Entity by default
public class Application {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)    // AUTO_INCREMENT
	@Column(name="application_id")
	private long id;
	@Column(nullable=false)
	private String title;
	@Column(nullable=false, name="application_email")
	private String email;
	@Enumerated(EnumType.STRING)
	private Field field;
	@Enumerated(EnumType.STRING)
	private WorkType workType;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="company_id", referencedColumnName="company_id", nullable=false)
	private Company company;
	@Column(nullable=false, name="application_date")
	private int date;
	private int deadline;
	private double salary;
	@Column(length=64)
	private String location;
	@Column(length=64)
	private String degreeRequired;
	/**
	 * In months
	 */
	private int duration;
	@Column(nullable=false, length=255)
	private String link;
	@Column(length=255)
	private String screenshotPath;
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Eligibility eligibility;
	@Column(length=255, name="application_description")
	private String description;
	@Column(length=255)
	private String resumePath;
	@Column(length=255)
	private String coverLetterPath;
	
	@OneToMany(mappedBy="application", cascade = CascadeType.ALL, fetch = FetchType.LAZY)    // Name of variable in KeywordID
	private List<Keyword> keywords;
	@OneToMany(mappedBy="application", cascade = CascadeType.ALL, fetch = FetchType.LAZY)    // Name of variable in Question
	private List<Question> questions;
	
	protected Application() {
		// For JPA
	}
	
	public Application(String title, String email, Field field, WorkType workType, Company company, int date, int deadline, double salary, String location, String degreeRequired, int duration, String link, String screenshotPath, Eligibility eligibility, String description, String resumePath, String coverLetterPath) {
		this.title = title;
		this.email = email;
		this.field = field;
		this.workType = workType;
		this.company = company;
		this.date = date;
		this.deadline = deadline;
		this.salary = salary;
		this.location = location;
		this.degreeRequired = degreeRequired;
		this.duration = duration;
		this.link = link;
		this.screenshotPath = screenshotPath;
		this.eligibility = eligibility;
		this.description = description;
		this.resumePath = resumePath;
		this.coverLetterPath = coverLetterPath;
	}
	
	/**
	 * For copying the instance to be deleted
	 * 
	 * @param c The original application instance to be deleted
	 */
	public Application(Application a) {
		this.id = a.getId();
		this.title = a.getTitle();
		this.email = a.getEmail();
		this.field = a.getField();
		this.workType = a.getWorkType();
		this.company = a.getCompany();
		this.date = a.getDate();
		this.deadline = a.getDate();
		this.salary = a.getSalary();
		this.location = a.getLocation();
		this.degreeRequired = a.getDegreeRequired();
		this.duration = a.getDuration();
		this.link = a.getLink();
		this.screenshotPath = a.getScreenshotPath();
		this.eligibility = a.getEligibility();
		this.description = a.getDescription();
		this.resumePath = a.getResumePath();
		this.coverLetterPath = a.getCoverLetterPath();
		
		this.keywords = a.getKeywords();
		this.questions = a.getQuestions();
	}
	
	
	// ===================================================
	// Getters and setters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public WorkType getWorkType() {
		return workType;
	}

	public void setWorkType(WorkType workType) {
		this.workType = workType;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDegreeRequired() {
		return degreeRequired;
	}

	public void setDegreeRequired(String degreeRequired) {
		this.degreeRequired = degreeRequired;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getScreenshotPath() {
		return screenshotPath;
	}

	public void setScreenshotPath(String screenshotPath) {
		this.screenshotPath = screenshotPath;
	}

	public Eligibility getEligibility() {
		return eligibility;
	}

	public void setEligibility(Eligibility eligibility) {
		this.eligibility = eligibility;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResumePath() {
		return resumePath;
	}

	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}

	public String getCoverLetterPath() {
		return coverLetterPath;
	}

	public void setCoverLetterPath(String coverLetterPath) {
		this.coverLetterPath = coverLetterPath;
	}
	
	@JsonIgnore
	public List<Keyword> getKeywords() {
		return this.keywords;
	}
	
	@JsonIgnore
	public List<Question> getQuestions() {
		return this.questions;
	}
	
	// ===========================================
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Application) || o == null) {
			return false;
		} else {
			return ((Application)o).getId() == this.getId();
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
		text.append("application_id: ");
		text.append(getId());
		text.append(", ");
		
		text.append("\n    ");
		text.append("title: ");
		text.append(getTitle());
		text.append(", ");
		
		text.append("\n    ");
		text.append("email: ");
		text.append(getEmail());
		text.append(", ");
		
		text.append("\n    ");
		text.append("field: ");
		text.append(getField());
		text.append(", ");
		
		text.append("\n    ");
		text.append("work_type: ");
		text.append(getWorkType());
		text.append(", ");
		
//		text.append("\n    ");
//		text.append("company: ");
//		text.append(getCompany());
//		text.append(", ");
		
		text.append("\n    ");
		text.append("application_date: ");
		text.append(getDate());
		text.append(", ");
		
		text.append("\n    ");
		text.append("deadline: ");
		text.append(getDeadline());
		text.append(", ");
		
		text.append("\n    ");
		text.append("salary: ");
		text.append(getSalary());
		text.append(", ");
		
		text.append("\n    ");
		text.append("location: ");
		text.append(getLocation());
		text.append(", ");
		
		text.append("\n    ");
		text.append("degree_required: ");
		text.append(getDegreeRequired());
		text.append(", ");
		
		text.append("\n    ");
		text.append("duration: ");
		text.append(getDuration());
		text.append(", ");
		
		text.append("\n    ");
		text.append("link: ");
		text.append(getLink());
		text.append(", ");
		
		text.append("\n    ");
		text.append("screenshot_path: ");
		text.append(getScreenshotPath());
		text.append(", ");
		
		text.append("\n    ");
		text.append("eligibility: ");
		text.append(getEligibility());
		text.append(", ");
		
		text.append("\n    ");
		text.append("application_description: ");
		text.append(getDescription());
		text.append(", ");
		
		text.append("\n    ");
		text.append("resume_path: ");
		text.append(getResumePath());
		text.append(", ");
		
		text.append("\n    ");
		text.append("cover_letter_path: ");
		text.append(getCoverLetterPath());
		
		text.append("\n}");
		
		return text.toString();
	}
}
