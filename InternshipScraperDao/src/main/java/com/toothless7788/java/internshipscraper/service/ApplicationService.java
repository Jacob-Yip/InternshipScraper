package com.toothless7788.java.internshipscraper.service;

import java.util.List;

import com.toothless7788.java.internshipscraper.entity.Application;
import com.toothless7788.java.internshipscraper.entity.Eligibility;
import com.toothless7788.java.internshipscraper.entity.Field;
import com.toothless7788.java.internshipscraper.entity.WorkType;

public interface ApplicationService {
	public Application addApplication(Application application);
	public List<Application> findAllByTitle(String title);
	public List<Application> findAllByField(Field field);
	public List<Application> findAllByWorkType(WorkType workType);
	public List<Application> findAllByEligibility(Eligibility eligibility);
	public void deleteApplication(Application application);
	public void deleteApplications(List<Application> applications);
	public void deleteAll();
	public List<Application> findAll();
	public Application findById(Long id);
	public Application findById(long id);
	public void deleteById(Long id);
	public void deleteById(long id);
}
