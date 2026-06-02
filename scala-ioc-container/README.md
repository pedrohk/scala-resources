# Spring Core IoC

## Overview

Spring Core IoC is a Scala 3 application that demonstrates the fundamental concepts of the Spring Framework's Inversion of Control (IoC) container and Dependency Injection (DI).

The project uses Spring's `AnnotationConfigApplicationContext` to manage application components and their dependencies through Java-based configuration. It serves as a practical example of how Spring can be integrated with Scala while maintaining clean architecture and strong separation of concerns.

## Technologies

* Scala 3.8.3
* Spring Framework 6.2.8
* Spring Core
* Spring Beans
* Spring Context
* ScalaTest 3.2.20
* SBT

## Project Structure

```text
src
├── main
│   └── scala
│       └── pedrohk
│           └── ioc
│               ├── Application.scala
│               ├── config
│               │   └── ApplicationConfiguration.scala
│               ├── model
│               │   ├── DeveloperProfile.scala
│               │   └── PlatformTeam.scala
│               ├── repository
│               │   ├── InMemoryProfileRepository.scala
│               │   └── ProfileRepository.scala
│               └── service
│                   ├── ProfileService.scala
│                   └── TeamService.scala
│
└── test
    └── scala
        └── pedrohk
            └── ioc
                ├── ApplicationTest.scala
                ├── config
                │   └── ApplicationConfigurationTest.scala
                ├── model
                │   ├── DeveloperProfileTest.scala
                │   └── PlatformTeamTest.scala
                └── service
                    ├── ProfileServiceTest.scala
                    └── TeamServiceTest.scala
```

## Architecture

The application follows a simple layered architecture:

```text
Configuration
      │
      ▼
Service Layer
      │
      ▼
Repository Layer
      │
      ▼
Domain Models
```

Spring manages object creation and dependency injection through the IoC container.

## Domain Model

### DeveloperProfile

Represents a developer profile.

Properties:

* Full name
* Specialization

Example:

```scala
new DeveloperProfile(
  "Pedro Henrique",
  "Spring Core"
)
```

### PlatformTeam

Represents a platform engineering team.

Example:

```scala
new PlatformTeam(
  "Lia Platform Team"
)
```

## Repository Layer

### ProfileRepository

Defines the contract for retrieving profile information.

Available operation:

```scala
def loadName(): String
```

### InMemoryProfileRepository

Provides an in-memory implementation of `ProfileRepository`.

Returned value:

```text
Pedro Henrique
```

## Service Layer

### ProfileService

Uses the repository layer through dependency injection.

Available operations:

```scala
profileName()
```

Returns the developer name.

```scala
greeting()
```

Returns a welcome message.

### TeamService

Provides access to team information.

Available operation:

```scala
teamLabel()
```

Returns the configured team name.

## Spring Configuration

All beans are configured through `ApplicationConfiguration`.

Registered beans:

* ProfileRepository
* ProfileService
* PlatformTeam
* TeamService
* DeveloperProfile

The IoC container automatically resolves and injects dependencies between components.

## Running the Application

Start the application using:

```bash
sbt run
```

The application creates and initializes the Spring IoC container.

## Running Tests

Execute all tests:

```bash
sbt test
```

## Test Coverage

The test suite validates:

* Application startup
* Spring bean creation
* Dependency injection
* Repository behavior
* Service behavior
* Domain model behavior
* IoC container configuration

## Example IoC Flow

```text
Spring Container
       │
       ▼
ProfileRepository
       │
       ▼
ProfileService
```

The service does not create its dependencies directly. Instead, Spring creates and injects them, demonstrating the core principle of Inversion of Control.

## Learning Objectives

This project demonstrates:

* Inversion of Control (IoC)
* Dependency Injection (DI)
* Bean registration
* Bean lifecycle management
* Java-based Spring configuration
* Spring and Scala interoperability
* Unit testing with ScalaTest

## Build

```bash
sbt compile
```

## Test

```bash
sbt test
```

## Run

```bash
sbt run
```
