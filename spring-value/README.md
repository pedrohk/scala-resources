# spring-value

A Scala 3.8.3 project demonstrating how to use Spring Framework `@Value` injection with Java Spring libraries.

This project shows multiple approaches for injecting configuration values into Scala classes while keeping the application modular, testable, and aligned with Spring dependency injection principles.

The implementation focuses on constructor injection, property resolution, bean configuration, and validation through automated tests.

## Technologies

* Scala 3.8.3
* Spring Framework
* Spring Core
* Spring Context
* ScalaTest 3.2.20
* SBT

## Project Goals

This project demonstrates:

* Injecting primitive and string values with `@Value`
* Reading values from Spring property sources
* Constructor-based dependency injection
* Creating configuration-driven services
* Building custom Spring beans
* Testing Spring configuration and value resolution
* Organizing Spring applications using packages and configuration classes

## Project Structure

```text
spring-value
├── build.sbt
├── src
│   ├── main
│   │   └── scala
│   │       └── pedrohk
│   │           └── springvalue
│   │               ├── config
│   │               │   ├── ProfileBeans.scala
│   │               │   └── ValueConfiguration.scala
│   │               ├── model
│   │               │   ├── ProfileSettings.scala
│   │               │   └── ProfileSummary.scala
│   │               └── service
│   │                   ├── ProfileSummaryService.scala
│   │                   └── ProfileValueService.scala
│   │
│   └── test
│       └── scala
│           └── pedrohk
│               └── springvalue
│                   └── config
│                       └── ValueConfigurationTest.scala
```

## Features

### Property Injection

Application values are injected directly into service constructors using Spring `@Value`.

Examples:

* Environment labels
* Owner information
* Service identifiers
* Feature flags
* Numeric configuration values

### Bean Composition

Configuration classes create and connect services and models using Spring-managed lifecycle.

### Automated Testing

Tests validate:

* Spring context initialization
* Correct property injection
* Bean creation
* Service interaction
* Value propagation
* Configuration consistency

All tests follow deterministic execution and are designed to run successfully using ScalaTest.

## Build

```bash
sbt compile
```

## Run Tests

```bash
sbt test
```

## Package

```bash
sbt package
```

## Example Workflow

1. Spring loads configuration.
2. Property values are resolved.
3. Services receive injected values.
4. Beans are created.
5. Tests validate the final object graph.
