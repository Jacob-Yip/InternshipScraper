package com.toothless7788.java.internshipscraper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toothless7788.java.internshipscraper.entity.Application;
import com.toothless7788.java.internshipscraper.entity.Eligibility;
import com.toothless7788.java.internshipscraper.entity.Field;
import com.toothless7788.java.internshipscraper.entity.WorkType;
import com.toothless7788.java.internshipscraper.exception.EmptyApplicationException;
import com.toothless7788.java.internshipscraper.repository.ApplicationRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Override
	public Application addApplication(Application application) throws EmptyApplicationException {
		if(application == null) {
			throw new EmptyApplicationException();
		}
		
		return applicationRepository.save(application);
	}

	@Override
	public List<Application> findAllByTitle(String title) {
		return applicationRepository.findAllByTitle(title);
	}

	@Override
	public List<Application> findAllByField(Field field) {
		return applicationRepository.findAllByField(field);
	}

	@Override
	public List<Application> findAllByWorkType(WorkType workType) {
		return applicationRepository.findAllByWorkType(workType);
	}

	@Override
	public List<Application> findAllByEligibility(Eligibility eligibility) {
		return applicationRepository.findAllByEligibility(eligibility);
	}

	@Override
	public void deleteApplication(Application application) throws EmptyApplicationException {
		if(application == null) {
			throw new EmptyApplicationException();
		}
		
		applicationRepository.deleteById(Long.valueOf(application.getId()));
	}

	@Override
	public void deleteApplications(List<Application> applications) {
		applicationRepository.deleteAll(applications);
	}

	@Override
	public void deleteAll() {
		applicationRepository.deleteAll();
	}

	@Override
	public List<Application> findAll() {
		return applicationRepository.findAll();
	}

	@Override
	public Application findById(Long id) {
		return findById(id.longValue());
	}

	@Override
	public Application findById(long id) {
		return applicationRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		deleteById(id.longValue());
	}

	@Override
	public void deleteById(long id) {
		Application target = new Application(null, null, null, null, null, 0, 0, 0, null, null, 0, null, null, null, null, null, null);
		target.setId(id);
		
		deleteApplication(target);
	}
	
}
