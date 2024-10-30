package com.toothless7788.java.internshipscraper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.toothless7788.java.internshipscraper.entity.Application;
import com.toothless7788.java.internshipscraper.entity.Company;
import com.toothless7788.java.internshipscraper.entity.CompanyCategory;
import com.toothless7788.java.internshipscraper.entity.Eligibility;
import com.toothless7788.java.internshipscraper.entity.Field;
import com.toothless7788.java.internshipscraper.entity.Keyword;
import com.toothless7788.java.internshipscraper.entity.KeywordID;
import com.toothless7788.java.internshipscraper.entity.Question;
import com.toothless7788.java.internshipscraper.entity.QuestionType;
import com.toothless7788.java.internshipscraper.entity.WorkType;
import com.toothless7788.java.internshipscraper.repository.ApplicationRepository;
import com.toothless7788.java.internshipscraper.repository.CompanyRepository;
import com.toothless7788.java.internshipscraper.repository.KeywordRepository;
import com.toothless7788.java.internshipscraper.repository.QuestionRepository;
import com.toothless7788.java.internshipscraper.service.ApplicationService;
import com.toothless7788.java.internshipscraper.service.CompanyService;
import com.toothless7788.java.internshipscraper.service.KeywordService;
import com.toothless7788.java.internshipscraper.service.QuestionService;

//@EnableTransactionManagement
@SpringBootApplication
public class InternshipScraperDaoApplication {
	
	private static final Logger log = LoggerFactory.getLogger(InternshipScraperDaoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(InternshipScraperDaoApplication.class, args);
	}
	
	/**
	 * 
	 * @param CompanyRepository companyRepository, ApplicationRepository applicationRepository, QuestionRepository questionRepository, KeywordRepository keywordRepository
	 * @param KeywordService keywordService, QuestionService questionService, ApplicationService applicationService, CompanyService companyService
	 * @return
	 */
	@Bean
//	public CommandLineRunner setUp(KeywordService keywordService, QuestionService questionService, ApplicationService applicationService, CompanyService companyService) {
	public CommandLineRunner setUp(CompanyRepository companyRepository, ApplicationRepository applicationRepository, QuestionRepository questionRepository, KeywordRepository keywordRepository) {
		return (args) -> {
			log.info("Run application");
			for(String arg: args) {
				log.info(arg);
			}
//			testRepository(companyRepository, applicationRepository, questionRepository, keywordRepository);
//			testService(keywordService, questionService, applicationService, companyService);
		};
	}
	
	private static void testService(KeywordService keywordService, QuestionService questionService, ApplicationService applicationService, CompanyService companyService) {
		Company[] companies = {
				new Company("company 1", Field.IT, CompanyCategory.GOVERNMENT, "address of company 1", 20240101, "company1.com", "china", "1st company"), 
				new Company("company 2", Field.IT, CompanyCategory.GOVERNMENT, "address of company 2", 20240202, "company2.com", "Finland", "2st company"), 
				new Company("company 3", Field.IT, CompanyCategory.GOVERNMENT, "address of company 3", 20240303, "company3.com", "USA", "3st company"), 
//				new Company(null, null, null, "address of company 3", 20240303, "company3.com", "USA", "3st company"), 
		};
		Application[] applications = {
				new Application("Application 1", "email1", Field.BUSINESS, WorkType.CASUAL, companies[0], 20240101, 20250101, 1000, "location 1", "1", 1, "link1", "path 1", Eligibility.ELIGIBLE, "description 1", "resume 1", "cover letter 1"), 
				new Application("Application 2", "email2", Field.BUSINESS, WorkType.CASUAL, companies[1], 20240202, 20250202, 2000, "location 2", "2", 2, "link2", "path 2", Eligibility.ELIGIBLE, "description 2", "resume 2", "cover letter 2"), 
				new Application("Application 3", "email3", Field.BUSINESS, WorkType.CASUAL, companies[2], 20240303, 20250303, 3000, "location 3", "3", 3, "link3", "path 3", Eligibility.ELIGIBLE, "description 3", "resume 3", "cover letter 3")
		};
		Question[] questions = {
				new Question(applications[0], "question 11", "answer 11", QuestionType.CHECKBOX), 
				new Question(applications[0], "question 12", "answer 12", QuestionType.CHECKBOX), 
				new Question(applications[0], "question 13", "answer 13", QuestionType.CHECKBOX), 
				new Question(applications[1], "question 21", "answer 21", QuestionType.CHECKBOX), 
				new Question(applications[1], "question 22", "answer 22", QuestionType.CHECKBOX), 
				new Question(applications[1], "question 23", "answer 23", QuestionType.CHECKBOX)
			};
		KeywordID[] keywordIDs = {
				new KeywordID("keyword11", applications[0].getId()), 
				new KeywordID("keyword12", applications[0].getId()), 
				new KeywordID("keyword13", applications[0].getId()), 
				new KeywordID("keyword21", applications[1].getId()), 
				new KeywordID("keyword22", applications[1].getId()), 
				new KeywordID("keyword23", applications[1].getId())
		};
		Keyword[] keywords = {
				new Keyword(keywordIDs[0], 11), 
				new Keyword(keywordIDs[1], 12), 
				new Keyword(keywordIDs[2], 13), 
				new Keyword(keywordIDs[3], 21), 
				new Keyword(keywordIDs[4], 22), 
				new Keyword(keywordIDs[5], 23)
		};
		
		// Reset everything -> I have linked every table and Spring JPA will help delete those related
		companyService.deleteAll();
		
		for(Company c: companies) {
			Company addedCompany = companyService.addCompany(c);
			log.info("Added company: " + addedCompany.toString());
		}
		log.info("================================");
		// Test deleteCompany()
//		List<Company> deleteCompanyList = new LinkedList<Company>(Arrays.asList(companies));
//		deleteCompanyList.remove(0);
//		Company cToBeDeleted = new Company("", Field.FINANCE, CompanyCategory.GOVERNMENT, "", 20240101, "", "", "");
//		cToBeDeleted.setId(0);
//		deleteCompanyList.add(0, cToBeDeleted);
//		
//		for(Company c: deleteCompanyList) {
//			if(c == null) {
//				log.info("null");
//				continue;
//			}
//			log.info(c.toString());
//		}
//		
//		companyService.deleteCompanies(deleteCompanyList);
		
		// Test find non-existent company
//		log.info(companyService.findById(-1).toString());
	}
	
	private static void testRepository(CompanyRepository companyRepository, ApplicationRepository applicationRepository, QuestionRepository questionRepository, KeywordRepository keywordRepository) {
		// Clean up
		companyRepository.deleteAll();
		applicationRepository.deleteAll();
		questionRepository.deleteAll();
		keywordRepository.deleteAll();
		
		// Test CompanyRepository
		List<Company> companies = new ArrayList<Company>();
		companies.add(new Company("company 1", Field.IT, CompanyCategory.GOVERNMENT, "address of company 1", 20240101, "company1.com", "china", "1st company"));
		companies.add(new Company("company 2", Field.IT, CompanyCategory.GOVERNMENT, "address of company 2", 20240202, "company2.com", "Finland", "2st company"));
		companies.add(new Company("company 3", Field.IT, CompanyCategory.GOVERNMENT, "address of company 3", 20240303, "company3.com", "USA", "3st company"));
		for(Company c: companies) {
			companyRepository.save(c);
		}
		
		Company company1 = companyRepository.findByName("company 1");
		log.info(company1.toString());
		
		log.info("==========================");
		
		log.info("Try ======");
		companyRepository.deleteById(Long.valueOf(208L));
		log.info("End of Try ======");
		
		// Test ApplicationRepository
		Application[] applications = {
				new Application("Application 1", "email1", Field.BUSINESS, WorkType.CASUAL, companies.get(0), 20240101, 20250101, 1000, "location 1", "1", 1, "link1", "path 1", Eligibility.ELIGIBLE, "description 1", "resume 1", "cover letter 1"), 
				new Application("Application 2", "email2", Field.BUSINESS, WorkType.CASUAL, companies.get(1), 20240202, 20250202, 2000, "location 2", "2", 2, "link2", "path 2", Eligibility.ELIGIBLE, "description 2", "resume 2", "cover letter 2"), 
				new Application("Application 3", "email3", Field.BUSINESS, WorkType.CASUAL, companies.get(2), 20240303, 20250303, 3000, "location 3", "3", 3, "link3", "path 3", Eligibility.ELIGIBLE, "description 3", "resume 3", "cover letter 3")
		};
		for(Application a: applications) {
			applicationRepository.save(a);
		}
		
		List<Application> application2s = applicationRepository.findAllByCompany(companies.get(1));
		for(Application a2: application2s) {
			log.info(a2.toString());
		}
		
		log.info("==========================");
		
		// Test QuestionRepository
		Question[] questions = {
			new Question(applications[0], "question 11", "answer 11", QuestionType.CHECKBOX), 
			new Question(applications[0], "question 12", "answer 12", QuestionType.CHECKBOX), 
			new Question(applications[0], "question 13", "answer 13", QuestionType.CHECKBOX), 
			new Question(applications[1], "question 21", "answer 21", QuestionType.CHECKBOX), 
			new Question(applications[1], "question 22", "answer 22", QuestionType.CHECKBOX), 
			new Question(applications[1], "question 23", "answer 23", QuestionType.CHECKBOX)
		};
		for(Question q: questions) {
			questionRepository.save(q);
		}
		
		List<Question> questionResults = questionRepository.findAllByApplication(applications[0]);
		for(Question result: questionResults) {
			log.info(result.toString());
		}
		
		log.info("==========================");
		
		// Test KeywordRepository
		KeywordID[] keywordIDs = {
				new KeywordID("keyword11", applications[0].getId()), 
				new KeywordID("keyword12", applications[0].getId()), 
				new KeywordID("keyword13", applications[0].getId()), 
				new KeywordID("keyword21", applications[1].getId()), 
				new KeywordID("keyword22", applications[1].getId()), 
				new KeywordID("keyword23", applications[1].getId())
		};
		Keyword[] keywords = {
				new Keyword(keywordIDs[0], 11), 
				new Keyword(keywordIDs[1], 12), 
				new Keyword(keywordIDs[2], 13), 
				new Keyword(keywordIDs[3], 21), 
				new Keyword(keywordIDs[4], 22), 
				new Keyword(keywordIDs[5], 23)
		};
		for(Keyword k: keywords) {
			keywordRepository.save(k);
		}
		
		List<Keyword> keywordResults = keywordRepository.findAllByID(new KeywordID("keyword11", applications[0].getId()));
		for(Keyword keywordResult: keywordResults) {
			log.info(keywordResult.toString());
		}
		
		List<Keyword> keywordApplicationIDs = keywordRepository.findAllByApplicationID(applications[0].getId());
		for(Keyword keywordApplicationID: keywordApplicationIDs) {
			log.info(keywordApplicationID.toString());
			log.info(String.valueOf(keywordRepository.existsByID(keywordApplicationID.getID())));
		}
		
		log.info(String.valueOf(keywordRepository.existsByID(null)));
		
	}

}
