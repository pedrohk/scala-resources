# Spring JPA Transactions

## Overview

Spring JPA Transactions is a Scala 3 application built with Spring Boot and Spring Data JPA. The project demonstrates how to configure and manage transactional operations using the Java Spring ecosystem together with Scala.

The application includes:

* Spring Boot integration
* Spring Data JPA repositories
* Declarative transaction management
* REST endpoints
* H2 in-memory database
* Immutable-style domain usage
* Service layer abstraction
* Comprehensive unit testing
* Java 25 compatibility
* Scala 3.8.3 support

---

# Technologies

* Scala 3.8.3
* Spring Boot 3.5.0
* Spring Data JPA
* Spring Web
* Hibernate ORM
* H2 Database
* ScalaTest 3.2.20
* Mockito 5.23.0
* SBT

---

# Project Structure

```text id="hzgxq0"
src
├── main
│   ├── resources
│   │   └── application.properties
│   │
│   └── scala
│       └── pedrohk
│           └── jpa
│               ├── controller
│               │   └── DeveloperAccountController.scala
│               ├── model
│               │   └── DeveloperAccount.scala
│               ├── repository
│               │   └── DeveloperAccountRepository.scala
│               ├── service
│               │   └── DeveloperAccountService.scala
│               └── Application.scala
│
└── test
    └── scala
        └── pedrohk
            └── jpa
│               ├── controller
│               │   └── DeveloperAccountControllerTest.scala
│               ├── repository
│               │   └── DeveloperAccountRepositoryTest.scala
│               ├── service
│               │   └── DeveloperAccountServiceTest.scala
│               └── ApplicationTest.scala
```

---

# Features

## Spring Transaction Management

The application enables transaction management using:

```scala id="q5p5i6"
@EnableTransactionManagement
```

The service layer uses declarative transactions with:

```scala id="b2y0rd"
@Transactional
```

---

# Domain Model

## DeveloperAccount

```scala id="9n2nwd"
class DeveloperAccount
```

Fields include:

* Identifier
* Owner
* Technology stack
* Credits

Example:

```scala id="q2r7tw"
new DeveloperAccount(
  "Pedro Henrique",
  "Spring",
  80
)
```

---

# Repository Layer

## DeveloperAccountRepository

```scala id="zfh6jw"
trait DeveloperAccountRepository
  extends JpaRepository[
    DeveloperAccount,
    java.lang.Long
  ]
```

Responsibilities include:

* Entity persistence
* Query abstraction
* Transaction participation
* JPA integration

---

# Service Layer

## DeveloperAccountService

The service layer contains transactional business operations.

### Account Creation

```scala id="wnmdps"
createAccount(
  owner,
  stack,
  credits
)
```

### Credit Transfer

```scala id="k6qg2j"
transferCredits(
  source,
  destination,
  amount
)
```

The transfer operation is transactional and guarantees consistency between both entities.

---

# Transaction Flow

## Successful Transaction

1. Source credits are reduced
2. Destination credits are increased
3. Both entities are persisted
4. Transaction commits successfully

---

## Failed Transaction

If validation fails:

```scala id="pzw4ba"
throw new IllegalArgumentException(
  "Insufficient credits"
)
```

The transaction is rolled back automatically.

---

# REST API

## Base Endpoint

```http id="rtj8f9"
http://localhost:8080/accounts
```

---

## Find Account By Owner

```http id="0u5i2j"
GET /accounts/{owner}
```

Example:

```http id="xrz8a5"
GET /accounts/Pedro Henrique
```

---

# Database Configuration

The project uses an H2 in-memory database.

```properties id="qvuh9w"
spring.datasource.url=jdbc:h2:mem:liajpa
```

Hibernate schema generation:

```properties id="jq2p88"
spring.jpa.hibernate.ddl-auto=create-drop
```

---

# Running Tests

```bash id="dwwf6n"
sbt test
```

---

# Running The Application

```bash id="3j2u9v"
sbt run
```

---

# Build Configuration

## Scala Version

```sbt id="yjlwm2"
scalaVersion := "3.8.3"
```

## Main Dependencies

```sbt id="qfjj1d"
spring-boot-starter
spring-boot-starter-web
spring-boot-starter-data-jpa
h2
scalatest
mockito-core
```

---

# Testing Strategy

The project contains deep unit tests covering:

* Controller behavior
* Transactional service logic
* Repository contracts
* Validation rules
* Application startup

The tests are designed to run reliably on Java 25.

---

# Compatibility

| Component   | Version                  |
| ----------- | ------------------------ |
| Scala       | 3.8.3                    |
| Java        | 25                       |
| Spring Boot | 3.5.0                    |
| ScalaTest   | 3.2.20                   |
| Hibernate   | Included via Spring Boot |

---

# Design Goals

This project demonstrates:

* Scala interoperability with Spring Boot
* Transaction management with JPA
* Declarative transactions
* Clean layered architecture
* Repository abstraction
* Modern JVM compatibility
* Reliable unit testing practices

