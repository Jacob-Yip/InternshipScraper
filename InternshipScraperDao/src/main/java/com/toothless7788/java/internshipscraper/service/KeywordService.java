package com.toothless7788.java.internshipscraper.service;

import java.util.List;

import com.toothless7788.java.internshipscraper.entity.Application;
import com.toothless7788.java.internshipscraper.entity.Keyword;
import com.toothless7788.java.internshipscraper.entity.KeywordID;

public interface KeywordService {
	public Keyword addKeyword(KeywordID keywordID);
	public List<Keyword> findAllByApplication(Application application);
	public List<Keyword> findAllByKeyword(String keyword);
	public List<Keyword> findAllByID(KeywordID keywordID);
	public void deleteKeyword(KeywordID keywordID);
	public void deletekeywords(List<KeywordID> keywordIDs);
	public void deleteAll();
	public List<Keyword> findAll();
	public Keyword findByKeywordApplicationID(String keyword, Long applicationID);
	public Keyword findByKeywordApplicationID(String keyword, long applicationID);
	public void deleteByKeywordAndApplicationID(String keyword, Long applicationID);
	public void deleteByKeywordAndApplicationID(String keyword, long applicationID);
	public Keyword findByID(KeywordID keyworID);
}
