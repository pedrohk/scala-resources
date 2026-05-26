# Spring Data JDBC

## Overview

Spring Data JDBC is a Scala 3 application built with Spring Boot and Spring Data JDBC. The project demonstrates how to create a layered backend application using Scala and the Java Spring ecosystem while maintaining clean architecture and immutable domain models.

The application includes:

* Spring Boot integration
* Spring Data JDBC repositories
* REST endpoints
* Service layer abstraction
* Immutable Scala models
* Comprehensive unit testing
* Java 25 compatibility
* Scala 3.8.3 support

---

# Technologies

* Scala 3.8.3
* Spring Boot 4.0.6
* Spring Data JDBC
* Spring Web
* H2 Database
* ScalaTest 3.2.20
* Mockito 5.23.0
* SBT

---

# Project Structure

```text id="8qq64q"
src
├── main
│   └── scala
│       └── pedrohk
│           └── datajdbc
│               ├── controller
│               │   └── DeveloperController.scala
│               ├── model
│               │   └── Developer.scala
│               ├── repository
│               │   └── DeveloperRepository.scala
│               ├── service
│               │   └── DeveloperService.scala
│               └── Application.scala
│
└── test
    └── scala
        └── pedrohk
│           └── datajdbc
│               ├── controller
│               │   └── DeveloperControllerTest.scala
│               ├── repository
│               │   └── DeveloperRepositoryTest.scala
│               ├── service
│               │   └── DeveloperServiceTest.scala
│               └── ApplicationTest.scala
```

---

# Features

## Spring Data JDBC Repository Support

The project uses Spring Data JDBC repositories for persistence abstraction.

```scala id="78cwn5"
trait DeveloperRepository
  extends CrudRepository[Developer, java.lang.Long]
```

---

# Domain Model

## Developer

```scala id="a7cg6x"
case class Developer(
  id: java.lang.Long,
  fullName: String,
  specialty: String,
  experienceYears: Int,
  active: Boolean
)
```

The application uses immutable Scala case classes for domain representation.

---

# REST API

## Base Endpoint

```http id="8v2a3u"
http://localhost:8080/developers
```

---

# Available Endpoints

## Create Developer

```http id="h9sn25"
POST /developers
```

Example body:

```json id="m4sq6r"
{
  "id": 1,
  "fullName": "Pedro Henrique",
  "specialty": "Spring Data JDBC",
  "experienceYears": 8,
  "active": true
}
```

---

## Find Developer By Identifier

```http id="mjlwmg"
GET /developers/{identifier}
```

---

## Find Developers By Specialty

```http id="qf3y1u"
GET /developers/specialty/{specialty}
```

---

## Find Active Developers

```http id="0z8s6z"
GET /developers/active
```

---

## Delete Developer

```http id="cahih2"
DELETE /developers/{identifier}
```

---

# Service Layer

The service layer isolates business logic from the controller layer.

Responsibilities include:

* Repository interaction
* Domain processing
* Data filtering
* Application orchestration

Example:

```scala id="2nmh9k"
class DeveloperService(
  developerRepository: DeveloperRepository
)
```

---

# Testing

The project contains deep unit tests for:

* Controller behavior
* Service logic
* Repository contracts
* Application startup

The testing strategy avoids problematic inline instrumentation issues on Java 25.

---

# Running Tests

```bash id="i2l7oc"
sbt test
```

---

# Running The Application

```bash id="q2bxoi"
sbt run
```

---

# Build Configuration

## Scala Version

```sbt id="7qdr7q"
scalaVersion := "3.8.3"
```

## Main Dependencies

```sbt id="23xch2"
spring-boot-starter
spring-boot-starter-web
spring-boot-starter-data-jdbc
scalatest
mockito-core
```

---

# Design Goals

This project was designed to demonstrate:

* Scala interoperability with Spring Boot
* Spring Data JDBC usage in Scala
* Clean layered backend architecture
* Immutable domain modeling
* Reliable unit testing on modern JVMs
* Practical enterprise-oriented Scala development

---

# Compatibility

| Component   | Version |
| ----------- | ------- |
| Scala       | 3.8.3   |
| Java        | 25      |
| Spring Boot | 4.0.6   |
| ScalaTest   | 3.2.20  |
