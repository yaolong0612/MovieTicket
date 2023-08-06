# Movie Ticket

## Description

Coding test for Sportsbet interview.

## Technology and Library

- Java 17
- Spring Boot
- Swagger
- Maven
- lombok
- JUnit
- Mockito

## Assumption

1. Age range is from 0 to 120.
2. Requests with empty customers are not accepted
3. The transaction ID in the request and response should be identical

## Api documentation

- http://localhost:8080/swagger-ui/index.html
- http://localhost:8080/v3/api-docs

## Quick Start

### Application Test

**On Mac or Linux**:

```sh
./mvnw test
```

**On Windows**:

```sh
mvnw.cmd test
```

### Application Package

**On Mac or Linux**:

```sh
./mvnw package
```

**On Windows**:

```sh
mvnw.cmd package
```

### Application Verify

**On Mac or Linux**:

```sh
./mvnw verify
```

**On Windows**:

```sh
mvnw.cmd verify
```

### Application Run

**On Mac or Linux**:

```sh
./mvnw spring-boot:run
```

**On Windows**:

```sh
mvnw.cmd spring-boot:run
```
