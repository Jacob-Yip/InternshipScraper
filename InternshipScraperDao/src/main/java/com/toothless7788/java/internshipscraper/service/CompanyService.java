package com.toothless7788.java.internshipscraper.service;

import java.util.List;

import com.toothless7788.java.internshipscraper.entity.Company;
import com.toothless7788.java.internshipscraper.entity.CompanyCategory;
import com.toothless7788.java.internshipscraper.entity.Field;

public interface CompanyService {
	public Company addCompany(Company company);
	public Company findByCompanyName(String companyName);
	public List<Company> findAllByField(Field field);
	public List<Company> findAllByCategory(CompanyCategory category);
	public List<Company> findAllByCountryOfOrigin(String countryOfOrigin);
	public void deleteCompany(Company company);
	public void deleteCompanies(List<Company> companies);
	public void deleteAll();
	public List<Company> findAll();
	public Company findById(Long id);
	public Company findById(long id);
	public void deleteById(Long id);
	public void deleteById(long id);
}
