package com.toothless7788.java.internshipscraper.service;

import java.util.List;

import com.toothless7788.java.internshipscraper.entity.Question;
import com.toothless7788.java.internshipscraper.entity.QuestionType;

public interface QuestionService {
	public Question addQuestion(Question question);
	public List<Question> findAllByQuestion(String question);
	public List<Question> findAllByQuestionType(QuestionType questionType);
	public void deleteQuestion(Question question);
	public void deleteQuestions(List<Question> questions);
	public void deleteAll();
	public List<Question> findAll();
	public Question findById(Long id);
	public Question findById(long id);
	public void deleteById(Long id);
	public void deleteById(long id);
}
