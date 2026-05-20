# Spring REST Client

A lightweight educational implementation inspired by Spring's `RestTemplate`, built with Scala 3. This project demonstrates how a REST client abstraction can handle HTTP-like GET and POST operations, response wrapping, and exception handling using a mock HTTP layer for testing purposes.

The project focuses on clean architecture, generic response handling, and unit testing with ScalaTest.

---

## Features

* Simplified `RestTemplate` implementation
* Mock HTTP request handling
* Generic `ResponseEntity<T>` wrapper
* GET and POST request simulation
* Automatic exception handling for invalid responses
* Thread-safe route storage using `ConcurrentHashMap`
* Unit testing with ScalaTest

---

## Project Structure

```text
spring-rest-client
│
├── build.sbt
├── src
│   ├── main
│   │   └── scala
│   │       └── springrest
│   │           ├── MockHttpHandler.scala
│   │           ├── ResponseEntity.scala
│   │           ├── RestClientException.scala
│   │           └── RestTemplate.scala
│   │
│   └── test
│       └── scala
│           └── springrest
│               └── RestTemplateTest.scala
```

---

# Technologies

* Scala 3.8.3
* ScalaTest 3.2.20
* JVM Concurrent Collections

---

# Core Components

## ResponseEntity

A generic wrapper that stores:

* Response body
* HTTP status code

Example:

```scala
val response = new ResponseEntity[String]("OK", 200)

println(response.getBody)
println(response.getStatusCode)
```

---

## MockHttpHandler

Simulates a minimal HTTP server behavior.

Supports:

* GET route registration
* POST route registration
* Route execution
* Mocked status codes

Example:

```scala
val handler = new MockHttpHandler()

handler.registerGetRoute(
  "http://example.com",
  """{"message":"success"}"""
)
```

---

## RestTemplate

Main client abstraction inspired by Spring Framework.

Supported operations:

| Method            | Description                               |
| ----------------- | ----------------------------------------- |
| `getForEntity()`  | Returns full response metadata            |
| `getForObject()`  | Returns only response body                |
| `postForEntity()` | Executes POST and returns response entity |
| `postForObject()` | Executes POST and returns only body       |

---

# Example Usage

## GET Request

```scala
val handler = new MockHttpHandler()

handler.registerGetRoute(
  "http://example.com",
  """{"id":1}"""
)

val restTemplate = new RestTemplate(handler)

val response = restTemplate.getForEntity("http://example.com")

println(response.getStatusCode)
println(response.getBody)
```

---

## POST Request

```scala
val handler = new MockHttpHandler()

val payload =
  """{"name":"Pedro Henrique"}"""

handler.registerPostRoute(
  "http://example.com",
  payload
)

val restTemplate = new RestTemplate(handler)

val response =
  restTemplate.postForEntity(
    "http://example.com",
    payload
  )

println(response.getStatusCode)
println(response.getBody)
```

---

# Exception Handling

The project includes a custom runtime exception:

```scala
RestClientException
```

It is thrown when:

* GET requests return non-200 status codes
* POST requests return non-201 status codes

Example:

```scala
assertThrows[RestClientException] {
  restTemplate.getForEntity("http://example.com")
}
```

---

# Running Tests

Execute all tests with:

```bash
sbt test
```

---

# Test Coverage

The test suite validates:

* Successful GET requests
* Successful POST requests
* Raw body extraction
* Exception handling
* Invalid request scenarios
* HTTP status validation

---

# Design Goals

This project was created to demonstrate:

* REST client abstraction design
* Generic programming in Scala
* Exception-driven request validation
* Thread-safe mock infrastructure
* Unit testing practices
* Simplified HTTP communication flow

---

# Future Improvements

Possible enhancements include:

* PUT and DELETE support
* Request headers
* JSON serialization/deserialization
* Asynchronous requests
* Real HTTP networking
* Generic object mapping
* Request interceptors

---

# Build

## Compile

```bash
sbt compile
```

## Run Tests

```bash
sbt test
```
