# CompanyHub

## Description

The Company Hub application is designed to create a .csv file in a GitHub repository filled with data about companies and transfer this data to other branches in the corresponding .csv format. Application includes GitHub REST API client for interacting with the repository, a service that implements the main business requirements, a mapper for data transformation, and tests that demonstrate the execution of these requirements.

- Generates companies with the following fields: Name, Number of Employees, Number of Customers, and Country.
- Inserts records into the corresponding CSV file.
- Merges branches.
- Asserts that files contain the same number of records.
- Asserts that all fields contain the same values in both files.
- Throws a relevant error in case any assertion is not true.

## Requirements

- Java 17 or higher
- Maven 3.8.x+

## Test Execution

In order to start test run command in the terminal:
```shell
mvn -Dtest=CompanyHubServiceTest test
```

## Stack

- Java 17
- Spring Boot 3
- RestAssured 5.4.0
- Lombok
- Mapstruct
- Apache Commons Lang3
- Apache Commons CSV
- Spring Test (AssertJ, JUnit 5)