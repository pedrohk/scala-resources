# Spring Injection (Scala 3)

A Scala 3.8.3 project built with Spring Framework that demonstrates Dependency Injection using both Constructor Injection and Setter Injection.

This application shows how Spring's IoC container manages dependencies while keeping the code modular, testable, and easy to evolve.

The project intentionally uses a lightweight architecture to focus on injection patterns instead of infrastructure complexity.

---

# Features

* Constructor Injection
* Setter Injection
* Spring IoC Container
* Manual Spring Configuration
* Repository abstraction
* In-memory data source
* Scala 3 with Spring libraries
* Automated tests using ScalaTest

---

# Project Structure

```text
spring-injection
│
├── build.sbt
│
└── src
    ├── main
    │   └── scala
    │       └── pedrohk
    │           └── injection
    │               ├── Application.scala
    │               │
    │               ├── config
    │               │   └── InjectionConfiguration.scala
    │               │
    │               ├── model
    │               │   └── DeveloperProfile.scala
    │               │
    │               ├── repository
    │               │   ├── ProfileRepository.scala
    │               │   └── InMemoryProfileRepository.scala
    │               │
    │               └── service
    │                   ├── ConstructorInjectedProfileService.scala
    │                   └── SetterInjectedProfileService.scala
    │
    └── test
        └── scala
            └── pedrohk
                └── injection
```

---

# Technologies

| Technology       | Version           |
| ---------------- | ----------------- |
| Scala            | 3.8.3             |
| Spring Framework | 7.x               |
| ScalaTest        | 3.2.20            |
| SBT              | Latest compatible |

---

# Dependency Injection Approaches

## Constructor Injection

Dependencies are supplied during object creation.

Advantages:

* Immutable dependencies
* Easier testing
* Explicit object requirements
* Preferred for mandatory dependencies

Example flow:

```text
Repository
    ↓
ConstructorInjectedProfileService
```

---

## Setter Injection

Dependencies are assigned after object creation.

Advantages:

* Flexible configuration
* Optional dependencies
* Late initialization

Example flow:

```text
Service
   ↓
setRepository(...)
```

---

# Domain Model

The application works with developer profiles.

Each profile contains:

```text
id
name
mentor
```

Sample data:

```text
1 → Pedro Henrique → Lia
2 → Caio Ventura → Lia
```

---

# Running the Application

Compile:

```bash
sbt compile
```

Run:

```bash
sbt run
```

---

# Running Tests

Execute all tests:

```bash
sbt test
```

Execute a specific suite:

```bash
sbt "testOnly pedrohk.injection.config.InjectionConfigurationTest"
```

---

# Test Coverage

The test suite validates:

* Domain model behavior
* Repository queries
* Constructor Injection
* Setter Injection
* Bean creation
* Spring context wiring
* Application startup

Tests avoid runtime bytecode instrumentation and do not require Mockito.

---

# Architecture

```text
Application
    ↓
Configuration
    ↓
Repository
    ↓
Services
    ↓
Model
```

---

# Build

```bash
sbt clean compile test
```

Expected result:

```text
All tests passed
```

---

# Learning Goals

This project is designed to demonstrate:

* How Spring resolves dependencies
* Differences between constructor and setter injection
* Dependency inversion
* Testable service design in Scala
* Spring Core fundamentals

```
```
