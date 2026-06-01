# Spring Security Authorize HTTP Requests

## Overview

Spring Security Authorize HTTP Requests is a Scala 3 application built with Spring Boot and Spring Security. The project demonstrates how to configure authorization rules using `HttpSecurity` and `SecurityFilterChain` while keeping a clean layered architecture.

The application includes:

* Spring Boot integration
* Spring Security configuration
* HTTP request authorization
* SecurityFilterChain configuration
* REST endpoints
* Layered architecture
* Scala 3 interoperability with Spring
* Unit testing with ScalaTest
* Java 25 compatibility

---

# Technologies

* Scala 3.8.3
* Spring Boot 3.5.0
* Spring Security
* Spring Web
* ScalaTest 3.2.20
* Mockito 5.23.0
* SBT

---

# Project Structure

```text
src
├── main
│   ├── resources
│   │   └── application.properties
│   │
│   └── scala
│       └── pedrohk
│           └── security
│               ├── Application.scala
│               ├── config
│               │   └── SecurityConfiguration.scala
│               ├── controller
│               │   └── DeveloperPortalController.scala
│               ├── model
│               │   └── DeveloperProfile.scala
│               └── service
│                   └── DeveloperProfileService.scala
│
└── test
    └── scala
        └── pedrohk
            └── security
                ├── ApplicationTest.scala
                ├── config
                │   └── SecurityConfigurationTest.scala
                ├── controller
                │   └── DeveloperPortalControllerTest.scala
                ├── model
                │   └── DeveloperProfileTest.scala
                └── service
                    └── DeveloperProfileServiceTest.scala
```

---

# Features

## HTTP Request Authorization

Authorization rules are configured through Spring Security.

Public endpoints:

```http
GET /public/status
```

Protected endpoints:

```http
GET /portal/profile
GET /portal/lia
```

Requests to protected resources require authentication.

---

# Security Configuration

The project uses a dedicated `SecurityFilterChain`.

Example configuration:

```scala
@Bean
def securityFilterChain(
  httpSecurity: HttpSecurity
): SecurityFilterChain
```

Authorization rules:

| Endpoint Pattern  | Access        |
| ----------------- | ------------- |
| `/public/**`      | Permit All    |
| `/portal/**`      | Authenticated |
| Any Other Request | Authenticated |

HTTP Basic authentication is enabled.

---

# Domain Model

## DeveloperProfile

Represents a developer profile.

Fields:

| Field          | Type   |
| -------------- | ------ |
| owner          | String |
| specialization | String |
| activeProjects | Int    |

Example:

```scala
new DeveloperProfile(
  "Pedro Henrique",
  "Spring Security",
  8
)
```

---

# Service Layer

## DeveloperProfileService

Provides business operations for developer profiles.

### Available Methods

#### Load Pedro Henrique Profile

```scala
loadPedroHenriqueProfile()
```

#### Load Lia Profile

```scala
loadLiaProfile()
```

#### Validate Access

```scala
validateAccess(profile)
```

#### Calculate Total Projects

```scala
totalProjects(
  primary,
  secondary
)
```

---

# REST API

## Status Endpoint

### Request

```http
GET /public/status
```

### Response

```text
Authorization configured
```

---

## Pedro Henrique Profile

### Request

```http
GET /portal/profile
```

### Response

```json
{
  "owner": "Pedro Henrique",
  "specialization": "Spring Security",
  "activeProjects": 8
}
```

---

## Lia Profile

### Request

```http
GET /portal/lia
```

### Response

```json
{
  "owner": "Lia",
  "specialization": "Platform Governance",
  "activeProjects": 4
}
```

---

# Running The Application

Start the application:

```bash
sbt run
```

Application URL:

```text
http://localhost:8080
```

---

# Running Tests

Execute all tests:

```bash
sbt test
```

The test suite validates:

* Application startup
* Security configuration
* Controller behavior
* Service logic
* Model construction
* Authorization-related business rules

---

# Build Configuration

## Scala Version

```sbt
scalaVersion := "3.8.3"
```

## Main Dependencies

```text
spring-boot-starter
spring-boot-starter-web
spring-boot-starter-security
scalatest
mockito-core
```

---

# Testing Strategy

The project contains comprehensive tests covering:

* Application initialization
* Security configuration validation
* Password encoder behavior
* Controller responses
* Service business rules
* Domain model behavior

The tests avoid mocking Spring Security internals, providing stable execution on modern JVM versions.

---

# Architecture

```text
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Model
```

This architecture promotes:

* Separation of concerns
* Testability
* Maintainability
* Clear security boundaries

---

# Compatibility

| Component       | Version  |
| --------------- | -------- |
| Scala           | 3.8.3    |
| Java            | 25       |
| Spring Boot     | 3.5.0    |
| Spring Security | Included |
| ScalaTest       | 3.2.20   |

---

# Learning Goals

This project demonstrates:

* Spring Security integration with Scala
* SecurityFilterChain configuration
* HttpSecurity authorization rules
* Public and protected endpoints
* Layered application design
* Unit testing practices
* Modern Spring Boot development
