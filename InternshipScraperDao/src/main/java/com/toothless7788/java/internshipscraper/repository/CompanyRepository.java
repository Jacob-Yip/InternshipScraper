package com.toothless7788.java.internshipscraper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toothless7788.java.internshipscraper.entity.Company;
import com.toothless7788.java.internshipscraper.entity.CompanyCategory;
import com.toothless7788.java.internshipscraper.entity.Field;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	public Company findByName(String name);
	public List<Company> findAllByCountryOfOrigin(String countryOfOrigin);
	public List<Company> findAllByField(Field field);
	public List<Company> findAllByCategory(CompanyCategory category);
	public Company findById(long id);
}