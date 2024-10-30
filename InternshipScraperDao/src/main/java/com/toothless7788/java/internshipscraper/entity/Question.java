package com.toothless7788.java.internshipscraper.entity;

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
import jakarta.persistence.Table;

@Entity
@Table(name="Question")    // Just in case: it will be named according to @Entity by default
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="question_id")
	private long id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="application_id", referencedColumnName="application_id", nullable=false)
	private Application application;
	@Column(nullable=false, length=255)
	private String question;
	@Column(length=511)
	private String answer;
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private QuestionType questionType;
	
	protected Question() {
		// For JPA
	}
	
	public Question(Application application, String question, String answer, QuestionType questionType) {
		this.application = application;
		this.question = question;
		this.answer = answer;
		this.questionType = questionType;
	}
	
	public Question(Question q) {
		this.id = q.getId();
		this.application = q.getApplication();
		this.question = q.getQuestion();
		this.answer = q.getAnswer();
		this.questionType = q.getQuestionType();
	}

	// ===================================================
	// Getters and setters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}
	
	// ===========================================
		@Override
		public boolean equals(Object o) {
			if(!(o instanceof Question) || o == null) {
				return false;
			} else {
				//TODO Fix JPA LazyInitializationException
				return this.getId() == ((Question)o).getId()/* && this.getApplication().equals(o)*/;
			}
		}
		
		@Override
		public int hashCode() {
			int hash = 7;
		    hash = 31 * hash + (int) getId();
		    //TODO Fix JPA LazyInitializationException
//		    hash = 31 * hash + (getApplication() == null ? 0 : getApplication().hashCode());
		    return hash;
		}

		@Override
		public String toString() {
			StringBuilder text = new StringBuilder();
			text.append("{");
			
			text.append("\n    ");
			text.append("question_id: ");
			text.append(getId());
			text.append(", ");
			
//			text.append("\n    ");
//			text.append("application: ");
//			text.append(getApplication());
//			text.append(", ");
			
			text.append("\n    ");
			text.append("question: ");
			text.append(getQuestion());
			text.append(", ");
			
			text.append("\n    ");
			text.append("answer: ");
			text.append(getAnswer());
			text.append(", ");
			
			text.append("\n    ");
			text.append("question_type: ");
			text.append(getQuestionType());
			
			text.append("\n}");
			
			return text.toString();
		}
	
}
