# Spring Integration Test REST

A lightweight Scala project that simulates a REST-style integration testing environment inspired by Spring applications.
The project demonstrates how different application layers — controller, service, and repository — interact together during integration tests using ScalaTest.

## Features

* Simulated REST controller behavior
* In-memory repository using `ConcurrentHashMap`
* User registration and retrieval endpoints
* Basic JSON payload parsing
* Integration testing with ScalaTest
* Layered architecture similar to Spring applications
* Validation and error handling examples

---

# Project Structure

```text
spring-integration-test-rest
├── build.sbt
├── src
│   ├── main
│   │   └── scala
│   │       └── springintegration
│   │           ├── MockMvcResponse.scala
│   │           ├── User.scala
│   │           ├── UserController.scala
│   │           ├── UserRepository.scala
│   │           └── UserService.scala
│   └── test
│       └── scala
│           └── springintegration
│               └── UserIntegrationTest.scala
```

---

# Technologies

* Scala 3.8.3
* ScalaTest 3.2.20

---

# Architecture Overview

The application follows a simplified layered architecture:

## Controller Layer

`UserController` simulates REST endpoints:

* `POST /api/users`
* `GET /api/users/{name}`

It returns a custom `MockMvcResponse` object containing:

* HTTP-like status codes
* JSON response body

---

## Service Layer

`UserService` contains business logic:

* User registration
* Input validation
* User retrieval

Example validation:

```scala
if (name == null || name.trim.isEmpty) {
  throw new IllegalArgumentException("Name cannot be empty")
}
```

---

## Repository Layer

`UserRepository` acts as an in-memory database using:

```scala
ConcurrentHashMap[String, User]
```

Supported operations:

* Save user
* Find user by name
* Clear database

---

# Example REST Flow

## Register User

### Request

```http
POST /api/users
```

```json
{
  "name": "Pedro Henrique",
  "role": "Administrator"
}
```

### Response

```json
{
  "name": "Pedro Henrique",
  "role": "Administrator"
}
```

Status:

```text
201 Created
```

---

## Retrieve User

### Request

```http
GET /api/users/Pedro Henrique
```

### Response

```json
{
  "name": "Pedro Henrique",
  "role": "Administrator"
}
```

Status:

```text
200 OK
```

---

# Error Handling

The project includes examples of common REST error scenarios.

## User Not Found

```json
{
  "error": "User not found"
}
```

Status:

```text
404 Not Found
```

---

## Invalid JSON Payload

```json
{
  "error": "Invalid JSON payload"
}
```

Status:

```text
400 Bad Request
```

---

## Unknown Endpoint

```json
{
  "error": "Not Found"
}
```

Status:

```text
404 Not Found
```

---

# Running the Tests

Execute all integration tests with:

```bash
sbt test
```

---

# Test Coverage

The integration tests validate:

* Successful user registration
* Successful user retrieval
* Full controller → service → repository integration
* Invalid payload handling
* Unknown endpoint handling
* Missing user scenarios
* Database cleanup between tests

---

# Example Integration Test

```scala
test("Integration system should allow registering and retrieving Pedro Henrique profile via REST endpoints") {
  val payload = """{"name":"Pedro Henrique","role":"Administrator"}"""
  val postResponse = controller.performPost("/api/users", payload)

  postResponse.getStatus shouldBe 201

  val getResponse = controller.performGet("/api/users/Pedro Henrique")

  getResponse.getStatus shouldBe 200
}
```

---

# Educational Purpose

This project is designed for educational purposes to demonstrate:

* Integration testing concepts
* Layered backend architecture
* REST endpoint simulation
* Service orchestration
* Repository interaction
* Basic request/response handling

It provides a simplified alternative to full Spring Boot integration tests while preserving the core architectural concepts.

---

# Future Improvements

Possible enhancements include:

* Real JSON parser integration
* HTTP server support
* Dependency injection container
* Persistent database integration
* Request routing abstraction
* Validation framework
* Full REST framework implementation
* Async request handling
