package com.toothless7788.java.internshipscraper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toothless7788.java.internshipscraper.entity.Application;
import com.toothless7788.java.internshipscraper.entity.Company;
import com.toothless7788.java.internshipscraper.entity.Eligibility;
import com.toothless7788.java.internshipscraper.entity.Field;
import com.toothless7788.java.internshipscraper.entity.WorkType;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
	public List<Application> findAllByTitle(String title);
	public List<Application> findAllByEmail(String email);
	public List<Application> findAllByField(Field field);
	public List<Application> findAllByWorkType(WorkType workType);
	public List<Application> findAllByCompany(Company company);
	public List<Application> findAllByEligibility(Eligibility eligibility);
	public Application findById(long id);
}