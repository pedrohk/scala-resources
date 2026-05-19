# Spring Testing Environment

A lightweight testing framework inspired by the Spring ecosystem, built in Scala 3.
This project simulates a minimal environment configuration system, controller routing layer, and fluent HTTP testing API similar to Spring's `WebTestClient`.

It demonstrates how environment-based behavior, profile activation, response assertions, and endpoint testing can be implemented from scratch using clean object-oriented design and fluent APIs.

## Features

* Environment property management
* Active profile support (`dev`, `test`, `prod`, etc.)
* Simple API controller simulation
* HTTP-like request handling
* Fluent testing DSL inspired by Spring WebFlux
* Custom assertion framework
* ScalaTest integration
* Thread-safe environment storage using `ConcurrentHashMap`

---

# Project Structure

```text
src
├── main
│   └── scala
│       └── springtesting
│           ├── ApiController.scala
│           ├── BodyAssertions.scala
│           ├── Environment.scala
│           ├── Response.scala
│           ├── ResponseSpec.scala
│           ├── StatusAssertions.scala
│           ├── UriSpec.scala
│           └── WebTestClient.scala
│
└── test
    └── scala
        └── springtesting
            └── EnvironmentAndWebTestClientTest.scala
```

---

# Technologies

* Scala 3.8.3
* ScalaTest 3.2.20
* SBT

---

# Architecture Overview

## Environment

The `Environment` class is responsible for:

* Storing application properties
* Managing active profiles
* Providing fallback default values
* Supporting thread-safe concurrent access

Example:

```scala
val env = new Environment()

env.setProperty("app.name", "ScalaSpringEngine")
env.addActiveProfile("dev")
```

---

## ApiController

The `ApiController` simulates endpoint routing and profile-based access control.

### Supported Endpoints

| Endpoint | Method | Description                                    |
| -------- | ------ | ---------------------------------------------- |
| `/info`  | GET    | Returns application information                |
| `/admin` | GET    | Grants access only for non-production profiles |

Example response:

```text
App: ScalaSpringEngine, Stage: staging
```

---

## WebTestClient

The `WebTestClient` provides a fluent API for endpoint testing.

Example:

```scala
client.get()
  .uri("/info")
  .expectStatus.isOk
  .expectBody.contains("ScalaSpringEngine")
```

The API design is inspired by Spring's reactive testing utilities.

---

# Assertion Framework

The project includes a custom assertion layer composed of:

* `ResponseSpec`
* `StatusAssertions`
* `BodyAssertions`

Supported assertions include:

## Status Assertions

```scala
.expectStatus.isOk
.expectStatus.isForbidden
.expectStatus.isNotFound
```

## Body Assertions

```scala
.expectBody.equalsTo("Expected Value")
.expectBody.contains("substring")
```

Failures throw descriptive `AssertionError` exceptions.

---

# Running Tests

Run the full test suite using:

```bash
sbt test
```

---

# Example Test

```scala
test("WebTestClient should verify authorized status when active profile allows access") {
  val env = new Environment()
  env.addActiveProfile("dev")

  val controller = new ApiController(env)
  val client = WebTestClient.bindToController(controller)

  client.get().uri("/admin")
    .expectStatus.isOk
    .expectBody.equalsTo("Admin access granted for non-prod profile")
}
```

---

# Design Goals

This project was created to demonstrate:

* Fluent API design in Scala
* Lightweight testing framework architecture
* Environment/profile-driven behavior
* Custom assertion pipelines
* Encapsulation and clean package organization
* Thread-safe application state handling

---

# Possible Future Improvements

* Support for POST request bodies
* JSON serialization/deserialization
* Route parameter extraction
* Middleware/interceptor support
* Dependency injection container
* Annotation-based mappings
* Mock service support
* Async request handling
* More advanced assertions

---

# Build Configuration

## build.sbt

```scala
name := "spring-testing-environment"

version := "0.1.0"

scalaVersion := "3.8.3"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.20" % Test
)
```

---

# Inspiration

The project is heavily inspired by concepts from:

* Spring Framework
* Spring WebFlux
* Spring Test
* Reactive testing patterns
* Fluent assertion APIs


This project is open-source and available for educational and learning purposes.
