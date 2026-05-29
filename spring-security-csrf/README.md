# Spring Security CSRF Protection

## Overview

Spring Security CSRF Protection is a Scala 3 application built with Spring Boot and Spring Security. The project demonstrates how to configure and validate Cross-Site Request Forgery (CSRF) protection using the Java Spring ecosystem together with Scala.

The application includes:

* Spring Boot integration
* Spring Security configuration
* CSRF protection
* REST endpoints
* Layered architecture
* Scala 3 interoperability
* Unit testing with ScalaTest
* Java 25 compatibility
* Secure HTTP request handling

---

# Technologies

* Scala 3.8.3
* Spring Boot 3.5.0
* Spring Security 6.5.0
* Spring Web
* ScalaTest 3.2.20
* Mockito 5.23.0
* SBT

---

# Project Structure

```text
src
├── main
│   └── scala
│       └── pedrohk
│           └── security
│               ├── config
│               │   └── SecurityConfiguration.scala
│               ├── controller
│               │   └── DeveloperSessionController.scala
│               ├── model
│               │   └── DeveloperSession.scala
│               ├── service
│               │   └── DeveloperSessionService.scala
│               └── Application.scala
│
└── test
    └── scala
        └── pedrohk
            └── security
│               ├── config
│               │   └── SecurityConfigurationTest.scala
│               ├── controller
│               │   └── DeveloperSessionControllerTest.scala
│               ├── service
│               │   └── DeveloperSessionServiceTest.scala
│               └── ApplicationTest.scala
```

---

# Features

## Spring Security Integration

The application configures Spring Security using a dedicated security filter chain.

Configured features include:

* HTTP Basic authentication
* CSRF protection
* Request authorization
* Secure REST endpoints

---

# Security Configuration

## SecurityFilterChain

The project uses a modern Spring Security configuration style.

```scala
@Bean
def securityFilterChain(
  httpSecurity: HttpSecurity
): SecurityFilterChain
```

Security behavior:

* Every request requires authentication
* CSRF protection remains enabled
* Stateless REST security support

---

# CSRF Protection

CSRF protection is enabled automatically through Spring Security.

Protected operations include:

* POST requests
* PUT requests
* DELETE requests
* PATCH requests

Example protected endpoint:

```http
POST /sessions
```

Requests without a valid CSRF token are rejected automatically.

---

# Domain Model

## DeveloperSession

```scala
class DeveloperSession
```

Fields:

* owner
* activeProject

Example:

```scala
new DeveloperSession(
  "Pedro Henrique",
  "Secure Platform"
)
```

---

# Service Layer

## DeveloperSessionService

Business responsibilities:

* Session creation
* Project validation
* Request processing

Methods include:

```scala
buildSession(
  owner,
  activeProject
)
```

and:

```scala
validateProject(
  project
)
```

---

# REST API

## Base Endpoint

```http
http://localhost:8080/sessions
```

---

## Status Endpoint

```http
GET /sessions
```

Response:

```text
CSRF protection enabled
```

---

## Create Session Endpoint

```http
POST /sessions
```

Example JSON body:

```json
{
  "owner": "Lia Kuhn",
  "activeProject": "Spring Fortress"
}
```

---

# Running The Application

```bash
sbt run
```

The server starts at:

```text
http://localhost:8080
```

---

# Running Tests

```bash
sbt test
```

The test suite validates:

* Service logic
* Controller responses
* Security configuration
* Application startup
* Request handling behavior

---

# Build Configuration

## Scala Version

```sbt
scalaVersion := "3.8.3"
```

---

## Main Dependencies

```sbt
spring-boot-starter
spring-boot-starter-web
spring-boot-starter-security
spring-security-test
scalatest
mockito-core
```

---

# Testing Strategy

The project contains deep tests covering:

* Security configuration correctness
* REST controller behavior
* Service validation logic
* Request processing
* Object construction
* Authentication infrastructure

Tests are designed to work reliably with:

* Scala 3.8.3
* Java 25
* Spring Boot 3.5.x

---

# Compatibility

| Component       | Version |
| --------------- | ------- |
| Scala           | 3.8.3   |
| Java            | 25      |
| Spring Boot     | 3.5.0   |
| Spring Security | 6.5.0   |
| ScalaTest       | 3.2.20  |

---

# Architecture

The project follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Model
```

This structure improves:

* Maintainability
* Testability
* Security isolation
* Separation of responsibilities

---

# Design Goals

This project demonstrates:

* Spring Security integration with Scala
* CSRF protection configuration
* Secure REST API construction
* Scala interoperability with Java frameworks
* Layered service architecture
* Modern Spring Boot practices
* Reliable unit testing
