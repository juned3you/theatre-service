# theatre-service

[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![Coverage Status](https://coveralls.io/repos/github/codecentric/springboot-sample-app/badge.svg?branch=master)](https://coveralls.io/github/codecentric/springboot-sample-app?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

This project has implemented only two endpoints for th below requirement:
* Browse theatres currently running the show (movie selected) in the town, including show timing by a chosen date.
* Book movie tickets by selecting a theatre, timing, and preferred seats for the day.

## Requirements

For building and running the application you need:

- JDK 17+
- Maven 3.9.4+
- Lombok
- H2 Database (embedded)
- Spring boot 3.1.4

## Running the application locally

You can directly import this project in Intellj Idea Editor. There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.xyz.theatreService.TheatreServiceApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
or run from source base folder
```shell
mvnw spring-boot:run
```

## Test the application

The easiest way to test this application is using swagger. Open below URL in the browser

```shell
http://localhost:8080/swagger-ui.html
```

Or Alternatively, you can test using curl

Book Ticket Request:
```shell
curl -X 'POST' \
  'http://localhost:8080/bookings' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "theatreId": 1,
  "screenId": 1,
  "timingsId": 1,
  "seatId": 1,
  "showDate": "2023-10-15"
}'
```

Search Theatres:
```shell
curl -X 'GET' \
  'http://localhost:8080/theatres/search?showDate=2023-10-16&pageNumber=0&pageSize=10&sortBy=id' \
  -H 'accept: */*'
```


## Test Data
The test data are inserting via a Flyway migration scripts can be found at below location. For test, inserted show times for 2023-10-15 and 2023-10-16 only. Make sure you sure the same data. You can also modify the ``V2__insert_script.sql`` to add more test data.
```shell
theatre-service\src\main\resources\db\migration\V1__create_intial_table.sql
theatre-service\src\main\resources\db\migration\V2__insert_script.sql
```

