# Spring Cache With Caffeine

## Overview

Spring Cache With Caffeine is a Scala 3 application built with Spring Boot and Caffeine Cache. The project demonstrates how to integrate high-performance in-memory caching into a Spring-based Scala application using the Java Spring ecosystem.

The application provides:

* Spring Boot cache integration
* Caffeine cache configuration
* Service-layer caching
* Immutable Scala domain models
* Repository abstraction
* Comprehensive unit testing
* Java 25 compatibility
* Scala 3.8.3 support

---

# Technologies

* Scala 3.8.3
* Spring Boot 4.0.6
* Spring Cache
* Caffeine Cache
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
│           └── cache
│               ├── config
│               │   └── CacheConfiguration.scala
│               ├── model
│               │   └── Portfolio.scala
│               ├── repository
│               │   ├── PortfolioRepository.scala
│               │   └── InMemoryPortfolioRepository.scala
│               ├── service
│               │   └── PortfolioService.scala
│               └── Application.scala
│
└── test
    └── scala
        └── pedrohk
            └── cache
│               ├── config
│               │   └── CacheConfigurationTest.scala
│               ├── repository
│               │   └── InMemoryPortfolioRepositoryTest.scala
│               ├── service
│               │   └── PortfolioServiceTest.scala
│               └── ApplicationTest.scala
```

---

# Features

## Spring Cache Support

The application enables Spring caching with:

```scala
@EnableCaching
```

Caching is configured globally through a dedicated configuration class.

---

# Caffeine Cache Configuration

The application uses Caffeine as the cache provider.

Example configuration:

```scala
Caffeine
  .newBuilder()
  .maximumSize(100)
  .expireAfterWrite(
    10,
    TimeUnit.MINUTES
  )
```

Features include:

* Automatic expiration
* Maximum cache size control
* Fast in-memory access
* Lightweight runtime footprint

---

# Domain Model

## Portfolio

```scala
case class Portfolio(
  identifier: Long,
  owner: String,
  technology: String,
  repositoryCount: Int
)
```

The project uses immutable Scala case classes for domain representation.

---

# Repository Layer

The repository layer abstracts persistence operations.

```scala
trait PortfolioRepository
```

The implementation uses an in-memory concurrent storage structure.

---

# Service Layer

## PortfolioService

The service layer integrates Spring Cache annotations directly into business operations.

### Cached Read Operation

```scala
@Cacheable(Array("portfolio-cache"))
```

### Cache Eviction Operation

```scala
@CacheEvict(
  value = Array("portfolio-cache"),
  key = "#portfolio.identifier"
)
```

The service layer is responsible for:

* Cache orchestration
* Data retrieval
* Cache invalidation
* Repository coordination

---

# Cache Flow

## Read Operation

1. Request arrives
2. Spring checks cache
3. Cached value is returned if present
4. Repository is called if cache miss occurs
5. Result is stored in cache

---

## Update Operation

1. Portfolio is updated
2. Cache entry is evicted
3. Next request reloads fresh data

---

# Running Tests

```bash
sbt test
```

---

# Running The Application

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
spring-boot-starter
spring-boot-starter-cache
caffeine
scalatest
mockito-core
```

---

# Testing Strategy

The project contains deep unit tests covering:

* Cache configuration
* Repository behavior
* Service logic
* Cache-aware operations
* Application startup

The tests are designed to run reliably on Java 25 without ByteBuddy instrumentation issues.

---

# Compatibility

| Component   | Version |
| ----------- | ------- |
| Scala       | 3.8.3   |
| Java        | 25      |
| Spring Boot | 4.0.6   |
| ScalaTest   | 3.2.20  |
| Caffeine    | 3.2.2   |

---

# Design Goals

This project demonstrates:

* Scala interoperability with Spring Boot
* Spring Cache integration in Scala
* Caffeine cache usage
* Clean layered architecture
* Immutable Scala modeling
* Modern JVM compatibility
* Reliable unit testing practices
