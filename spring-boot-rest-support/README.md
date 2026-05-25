# Spring Boot REST Support

## Overview

Spring Boot REST Support is a Scala 3 project that demonstrates how to integrate modern Scala applications with the Java Spring Boot ecosystem using `RestTemplate` and REST-oriented service layers.

The project provides a clean layered architecture with:

* Spring Boot REST support
* External API communication using `RestOperations`
* Service and controller separation
* Immutable Scala domain models
* Deep unit testing with ScalaTest and Mockito
* Compatibility with Java 25
* Scala 3.8.3 support

The implementation focuses on interoperability between Scala and Spring Framework while maintaining idiomatic Scala code structure.

---

# Technologies

* Scala 3.8.3
* Spring Boot 4.0.6
* Spring Web
* ScalaTest 3.2.20
* Mockito
* ByteBuddy
* SBT

---

# Project Structure

```text
src
├── main
│   └── scala
│       └── pedrohk
│           ├── controller
│           │   └── ProfileController.scala
│           ├── model
│           │   ├── Profile.scala
│           │   └── ProfileResponse.scala
│           ├── service
│           │   ├── ProfileGateway.scala
│           │   └── ProfileService.scala
│           ├── config
│           │   └── RestClientConfig.scala
│           └── Application.scala
│
└── test
    └── scala
        └── pedrohk
            ├── controller
            │   └── ProfileControllerTest.scala
            ├── service
            │   ├── ProfileGatewayTest.scala
            │   └── ProfileServiceTest.scala
            └── ApplicationTest.scala
```

---

# Features

## REST Client Integration

The project uses Spring's `RestOperations` abstraction for HTTP communication.

```scala
restTemplate.exchange(
  url,
  HttpMethod.GET,
  null,
  classOf[ProfileResponse]
)
```

This allows easy mocking and better test isolation.

---

# Domain Model

## Profile

```scala
case class Profile(
  id: Long,
  owner: String,
  expertise: String,
  active: Boolean
)
```

## ProfileResponse

Represents the external API response model.

```scala
case class ProfileResponse(
  identifier: Long,
  displayName: String,
  specialty: String,
  enabled: Boolean
)
```

---

# Service Layer

The service layer maps external DTOs into internal domain models.

```scala
class ProfileService(profileGateway: ProfileGateway)
```

Responsibilities include:

* Calling remote services
* Mapping API responses
* Isolating business logic
* Protecting controller layer from transport concerns

---

# Controller Layer

The REST controller exposes profile endpoints.

## Endpoint

```http
GET /profiles/{identifier}
```

Example response:

```json
{
  "id": 7,
  "owner": "Pedro Henrique",
  "expertise": "Spring Boot",
  "active": true
}
```

---

# Testing Strategy

The project includes deep unit tests covering:

* REST gateway behavior
* Service mapping logic
* Controller responses
* Application bootstrapping

Tests were designed specifically to avoid Java 25 inline instrumentation problems.

## Important Testing Decision

Instead of mocking concrete Spring classes, the project:

* mocks only interfaces such as `RestOperations`
* uses fake implementations for service isolation
* avoids Mockito inline mock maker issues
* remains stable on Java 25

---

# Running Tests

```bash
sbt test
```

---

# Running the Application

```bash
sbt run
```

---

# Build Configuration

## Scala Version

```sbt
scalaVersion := "3.8.3"
```

## Main Dependencies

```sbt
org.springframework.boot:spring-boot-starter-web
org.scalatest:scalatest
org.mockito:mockito-core
org.mockito.scala:mockito-scala-scalatest
```

---

# Design Goals

This project was built to demonstrate:

* Scala and Spring Boot interoperability
* Clean REST architecture
* Reliable testing strategies on modern JDKs
* Practical Spring integration patterns in Scala
* Immutable domain-driven design

---

# Compatibility

| Component   | Version |
| ----------- | ------- |
| Scala       | 3.8.3   |
| Java        | 25      |
| Spring Boot | 4.0.6   |
| ScalaTest   | 3.2.20  |
