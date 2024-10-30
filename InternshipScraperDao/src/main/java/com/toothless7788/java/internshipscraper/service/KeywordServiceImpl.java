package com.toothless7788.java.internshipscraper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toothless7788.java.internshipscraper.entity.Application;
import com.toothless7788.java.internshipscraper.entity.Keyword;
import com.toothless7788.java.internshipscraper.entity.KeywordID;
import com.toothless7788.java.internshipscraper.exception.EmptyApplicationException;
import com.toothless7788.java.internshipscraper.exception.EmptyKeywordIDException;
import com.toothless7788.java.internshipscraper.repository.KeywordRepository;

@Service
public class KeywordServiceImpl implements KeywordService {
	@Autowired
	private KeywordRepository keywordRepository;
	
	@Override
	public Keyword addKeyword(KeywordID keywordID) throws EmptyKeywordIDException {
		if(keywordID == null) {
			throw new EmptyKeywordIDException();
		}
		
		if(keywordRepository.existsByID(keywordID)) {
			Keyword oldKeyword = keywordRepository.findByID(keywordID);
			return keywordRepository.save(new Keyword(keywordID, oldKeyword.getCount() + 1));    // Increment count by 1
		} else {
			return keywordRepository.save(new Keyword(keywordID, 0));
		}
	}
	
	@Override
	public List<Keyword> findAllByApplication(Application application) throws EmptyApplicationException {
		if(application == null) {
			throw new EmptyApplicationException();
		}
		
		return keywordRepository.findAllByApplicationID(application.getId());
	}

	@Override
	public List<Keyword> findAllByKeyword(String keyword) {
		return keywordRepository.findAllByKeyword(keyword);
	}

	@Override
	public List<Keyword> findAllByID(KeywordID keywordID) throws EmptyKeywordIDException {
		if(keywordID == null) {
			throw new EmptyKeywordIDException();
		}
		
		return keywordRepository.findAllByID(keywordID);
	}

	@Override
	public void deleteKeyword(KeywordID keywordID) throws EmptyKeywordIDException {
		if(keywordID == null) {
			throw new EmptyKeywordIDException();
		}
		
		keywordRepository.deleteById(keywordID);
	}

	@Override
	public void deletekeywords(List<KeywordID> keywordIDs) {
		keywordRepository.deleteAllById(keywordIDs);
	}

	@Override
	public void deleteAll() {
		keywordRepository.deleteAll();
	}

	@Override
	public List<Keyword> findAll() {
		return keywordRepository.findAll();
	}

	@Override
	public Keyword findByKeywordApplicationID(String keyword, Long applicationID) {
		return findByKeywordApplicationID(keyword, applicationID.longValue());
	}

	@Override
	public Keyword findByKeywordApplicationID(String keyword, long applicationID) {
		KeywordID targetID = new KeywordID(keyword, applicationID);
		return keywordRepository.findByID(targetID);
	}

	@Override
	public void deleteByKeywordAndApplicationID(String keyword, Long applicationID) {
		deleteByKeywordAndApplicationID(keyword, applicationID.longValue());
	}

	@Override
	public void deleteByKeywordAndApplicationID(String keyword, long applicationID) {
		KeywordID target = new KeywordID(keyword, applicationID);
		deleteKeyword(target);
	}

	@Override
	public Keyword findByID(KeywordID keyworID) {
		return keywordRepository.findByID(keyworID);
	}

}
