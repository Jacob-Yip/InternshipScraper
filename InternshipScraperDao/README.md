### This file contains the information of the backend application that connects database for InternshipScraper
#### Author: Toothless7788


### Set up
- Go to [spring initializer](https://start.spring.io/)
- #### Dependencies
    1. ```Spring Web```
    2. ```Spring Data JPA```
    3. ```Spring HATEOAS```
    4. ```Docker Compose Support```
- Java version: ```21```
- Spring Boot version: ```3.3.5```
- Project: ```Maven```

### Make RESTFul Request
```
# Remember to set up database (Refer to ../doc/design/DATABASE.md)
# Run the InternshipScraperDaoApplication.java file in InternshipScraperDao/
# You can use Postman or the few commands below to test it yourself
# Get
curl -v http://localhost:8080/application/all
curl -v http://localhost:8080/company/all
curl -v http://localhost:8080/question/all
curl -v http://localhost:8080/keyword/all
curl -v http://localhost:8080/application/one/{application_id}
curl -v http://localhost:8080/company/one/{company_id}
curl -v http://localhost:8080/question/one/{question_id}
curl -v http://localhost:8080/keyword/one/{keyword_id}

# Delete
curl --location --request DELETE 'http://localhost:8080/company/one/{company_id}' --data ''

# Post
#{
#    "name": "testCompany123", 
#    "field": "IT", 
#    "category": "GOVERNMENT", 
#    "address": "company address 123", 
#    "dateOfIncorporation": 20240112, 
#    "websiteLink": "https://test_domain.com", 
#    "countryOfOrigin": "UK", 
#    "description": "Optional company description"
#}
curl --location 'http://localhost:8080/company/' --header 'Content-Type: application/json' --data '{
    "name": "test Company 123",
    "field": "IT",
    "category": "GOVERNMENT",
    "address": "company address 123",
    "dateOfIncorporation": 20240112,
    "websiteLink": "https://test_domain.com",
    "countryOfOrigin": "UK",
    "description": "Optional company description"
}'
```
