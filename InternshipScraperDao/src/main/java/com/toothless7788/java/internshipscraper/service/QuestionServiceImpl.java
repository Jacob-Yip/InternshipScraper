package com.toothless7788.java.internshipscraper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toothless7788.java.internshipscraper.entity.Question;
import com.toothless7788.java.internshipscraper.entity.QuestionType;
import com.toothless7788.java.internshipscraper.exception.EmptyQuestionException;
import com.toothless7788.java.internshipscraper.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question addQuestion(Question question) throws EmptyQuestionException {
		if(question == null) {
			throw new EmptyQuestionException();
		}
		return questionRepository.save(question);
	}

	@Override
	public List<Question> findAllByQuestion(String question) {
		return questionRepository.findAllByQuestion(question);
	}

	@Override
	public List<Question> findAllByQuestionType(QuestionType questionType) {
		return questionRepository.findAllByQuestionType(questionType);
	}

	@Override
	public void deleteQuestion(Question question) throws EmptyQuestionException {
		if(question == null) {
			throw new EmptyQuestionException();
		}
		
		questionRepository.deleteById(Long.valueOf(question.getId()));
	}

	@Override
	public void deleteQuestions(List<Question> questions) {
		questionRepository.deleteAll(questions);
	}

	@Override
	public void deleteAll() {
		questionRepository.deleteAll();
	}

	@Override
	public List<Question> findAll() {
		return questionRepository.findAll();
	}

	@Override
	public Question findById(Long id) {
		return findById(id.longValue());
	}

	@Override
	public Question findById(long id) {
		return questionRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		deleteById(id.longValue());
	}

	@Override
	public void deleteById(long id) {
		Question target = new Question(null, null, null, null);
		target.setId(id);
		
		deleteQuestion(target);
	}
}