package com.toothless7788.java.internshipscraper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toothless7788.java.internshipscraper.entity.Company;
import com.toothless7788.java.internshipscraper.entity.CompanyCategory;
import com.toothless7788.java.internshipscraper.entity.Field;
import com.toothless7788.java.internshipscraper.exception.EmptyCompanyException;
import com.toothless7788.java.internshipscraper.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public Company addCompany(Company company) throws EmptyCompanyException {
		if(company == null) {
			throw new EmptyCompanyException();
		}
		
		return companyRepository.save(company);
	}

	@Override
	public Company findByCompanyName(String companyName) {
		return companyRepository.findByName(companyName);
	}

	@Override
	public List<Company> findAllByField(Field field) {
		return companyRepository.findAllByField(field);
	}

	@Override
	public List<Company> findAllByCategory(CompanyCategory category) {
		return companyRepository.findAllByCategory(category);
	}

	@Override
	public List<Company> findAllByCountryOfOrigin(String countryOfOrigin) {
		return companyRepository.findAllByCountryOfOrigin(countryOfOrigin);
	}

	@Override
	public void deleteCompany(Company company) throws EmptyCompanyException {
		if(company == null) {
			throw new EmptyCompanyException();
		}
		
		companyRepository.deleteById(Long.valueOf(company.getId()));
	}

	@Override
	public void deleteCompanies(List<Company> companies) {
		companyRepository.deleteAll(companies);
	}

	@Override
	public void deleteAll() {
		companyRepository.deleteAll();
	}

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	@Override
	public Company findById(Long id) {
		return companyRepository.findById(id.longValue());
	}

	@Override
	public Company findById(long id) {
		return companyRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		deleteById(id.longValue());
	}

	@Override
	public void deleteById(long id) {
		Company target = new Company(null, null, null, null, 0, null, null, null);
		target.setId(id);
		
		deleteCompany(target);
	}
	
}
