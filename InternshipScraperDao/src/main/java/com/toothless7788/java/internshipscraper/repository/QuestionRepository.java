package com.toothless7788.java.internshipscraper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toothless7788.java.internshipscraper.entity.Application;
import com.toothless7788.java.internshipscraper.entity.Question;
import com.toothless7788.java.internshipscraper.entity.QuestionType;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	public List<Question> findAllByApplication(Application application);
	public List<Question> findAllByQuestion(String question);
	public List<Question> findAllByAnswer(String answer);
	public List<Question> findAllByQuestionType(QuestionType questionType);
	public Question findById(long id);
}
