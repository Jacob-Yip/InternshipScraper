package com.toothless7788.java.internshipscraper.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.toothless7788.java.internshipscraper.entity.Keyword;
import com.toothless7788.java.internshipscraper.entity.KeywordID;

public interface KeywordRepository extends JpaRepository<Keyword, KeywordID> {
	//TODO Find by keyword only
	//TODO Find by application only
//	public List<Map<String, Object>> findByKeywordAndApplication(String keyword, Application application);
	public Keyword findByID(KeywordID keywordID);
	public boolean existsByID(KeywordID keywordID);
	public List<Keyword> findAllByID(KeywordID keywordID);
	@Query(nativeQuery=true, value="SELECT Keyword.keyword, Application.*, Keyword.count FROM Keyword INNER JOIN Application ON Keyword.application_id = Application.application_id WHERE Application.application_id=:applicationID;")
	public List<Keyword> findAllByApplicationID(@Param("applicationID") long applicationID);
	@Query(nativeQuery=true, value="SELECT Keyword.keyword, Application.*, Keyword.count FROM Keyword INNER JOIN Application ON Keyword.application_id = Application.application_id WHERE Keyword.keyword=:keyword;")
	public List<Keyword> findAllByKeyword(@Param("keyword") String keyword);
}