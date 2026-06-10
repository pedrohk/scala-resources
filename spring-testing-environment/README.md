# Spring Testing Environment

A complete Spring Boot + Scala 3 application demonstrating environment-aware services, REST APIs, configuration management, application testing, and endpoint verification using WebTestClient.

This project was built using Spring libraries for Java while keeping all application and test code implemented in Scala 3.

## Features

* Scala 3.8.3 application structure
* Spring Boot 4 integration
* REST API with Spring Web
* Reactive HTTP testing using WebTestClient
* Environment abstraction using Spring Environment
* Configuration classes with Spring Beans
* Service layer isolation
* Integration testing without Mockito
* ScalaTest-based testing strategy
* Constructor dependency injection

---

## Technologies

* Scala 3.8.3
* Spring Boot 4.0.6
* Spring Framework
* Spring Web
* Spring WebFlux
* ScalaTest 3.2.20
* SBT

---

## Project Structure

```text
spring-testing-webclient
├── build.sbt
├── src
│   ├── main
│   │   ├── resources
│   │   │   └── application.properties
│   │   └── scala
│   │       └── pedrohk
│   │           └── testingwebclient
│   │               ├── Application.scala
│   │               ├── config
│   │               │   └── ApplicationConfiguration.scala
│   │               ├── controller
│   │               │   └── ProfileController.scala
│   │               ├── model
│   │               │   ├── Profile.scala
│   │               │   └── ProfileSummary.scala
│   │               └── service
│   │                   ├── EnvironmentService.scala
│   │                   └── ProfileService.scala
│   │
│   └── test
│       └── scala
│           └── pedrohk
│               └── testingwebclient
│                   ├── config
│                   ├── controller
│                   ├── integration
│                   └── service
```

---

## Architecture

### EnvironmentService

Responsible for reading the current runtime environment.

Behavior:

* Reads `application.environment`
* Returns `local` when the property is absent

Example:

```text
application.environment=test
```

---

### ProfileService

Generates domain objects and summaries.

Produces:

```json
{
  "id": 10,
  "owner": "Pedro Henrique",
  "environment": "test",
  "active": true
}
```

Summary output:

```json
{
  "description": "Pedro Henrique-test"
}
```

---

### ProfileController

Exposes HTTP endpoints.

Endpoints:

| Method | Endpoint          | Description                |
| ------ | ----------------- | -------------------------- |
| GET    | /profiles/current | Returns the active profile |
| GET    | /profiles/summary | Returns profile summary    |

---

## Configuration

File:

```text
src/main/resources/application.properties
```

Example:

```properties
application.environment=test
```

Bean configuration:

```scala
@Bean
def ownerName(): String = {
  "Lia"
}
```

---

## Running the Application

Start:

```bash
sbt run
```

Default server:

```text
http://localhost:8080
```

Example requests:

Current profile:

```http
GET /profiles/current
```

Summary:

```http
GET /profiles/summary
```

---

## Testing

Run all tests:

```bash
sbt test
```

The project includes:

### Unit Tests

* EnvironmentServiceTest
* ProfileServiceTest
* ApplicationConfigurationTest
* ProfileControllerTest

Coverage includes:

* Environment resolution
* Default values
* Profile generation
* Summary generation
* Controller behavior

### Integration Tests

WebTestClientIntegrationTest

Coverage includes:

* Server startup
* Endpoint availability
* HTTP response validation
* Runtime environment configuration

---

## Testing Principles

This project intentionally avoids:

* Mockito
* Reflection-based mocking
* String-driven test descriptions

Tests are deterministic and instantiate dependencies explicitly.

---

## Build

Compile:

```bash
sbt compile
```

Run tests:

```bash
sbt test
```

Package:

```bash
sbt package
```

Clean:

```bash
sbt clean
```

---
