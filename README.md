## Project Name & Pitch

Mentor Matching app

An application uses data form uploaded csv file generates all possible combination of unique groups and calculates maximum average matching of employees.
Built with Spring Boot, Thymeleaf, Bootstrap

## Installation and Setup Instructions

Clone down this repository. You will need JDK 8 and Maven installed on your machine.  

Installation:

`mvn clean install`  

To Run Test Suite:  

`mvn clean test`  

To Start Server:

`mvn spring-boot:run`  

To Visit App:

`localhost:8080`  

To use REST Api

`POST localhost:8080/api/v1/upload-csv`