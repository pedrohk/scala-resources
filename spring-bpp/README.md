# Spring Bean Post Processor

A Scala 3 project demonstrating how to use Spring Framework Bean Post Processors to customize bean lifecycle behavior.

This project shows how Spring can intercept bean creation and apply custom initialization logic before and after a bean becomes available inside the application context.

The implementation is built with Scala 3.8.3 while using Spring Java libraries directly.

## Features

* Scala 3.8.3 project structure
* Spring Core dependency injection
* Custom BeanPostProcessor implementation
* Bean lifecycle customization
* Before initialization processing
* After initialization processing
* Spring Application Context integration
* Comprehensive automated tests using ScalaTest
* Constructor-based bean creation
* No external databases or HTTP services

## Project Structure

```text
src
├── main
│   └── scala
│       └── pedrohk
│           └── beanprocessor
│               ├── Application.scala
│               ├── config
│               │   └── BeanProcessorConfiguration.scala
│               ├── model
│               │   └── InitializationAudit.scala
│               ├── processor
│               │   └── ProfileInitializationPostProcessor.scala
│               ├── repository
│               └── service
│                   └── DeveloperProfileService.scala
│
└── test
    └── scala
        └── pedrohk
            └── beanprocessor
                ├── ApplicationTest.scala
                ├── config
                │   └── BeanProcessorConfigurationTest.scala
                ├── model
                │   └── InitializationAuditTest.scala
                ├── processor
                │   └── ProfileInitializationPostProcessorTest.scala
                └── service
                    └── DeveloperProfileServiceTest.scala
```

## Architecture

The application contains four main parts:

### DeveloperProfileService

A Spring-managed bean that represents a profile service.

Its state changes during bean initialization.

### ProfileInitializationPostProcessor

A custom Spring BeanPostProcessor responsible for:

* Updating bean properties before initialization
* Applying post-processing after initialization
* Injecting default runtime values

### InitializationAudit

A simple model that captures the final initialization state after processing.

### BeanProcessorConfiguration

Spring configuration responsible for registering all beans and wiring dependencies.

## Bean Lifecycle

```text
Bean Creation
      ↓
Dependency Injection
      ↓
postProcessBeforeInitialization()
      ↓
Bean Initialization
      ↓
postProcessAfterInitialization()
      ↓
Bean Ready
```

## Running the Application

Start the application:

```bash
sbt run
```

## Running Tests

Execute all tests:

```bash
sbt test
```

Run a single test:

```bash
sbt "testOnly pedrohk.beanprocessor.service.DeveloperProfileServiceTest"
```

## Testing Strategy

The project includes test coverage for:

* Model validation
* Service behavior
* Bean post-processing logic
* Bean lifecycle execution
* Spring context initialization
* End-to-end configuration verification
* Application startup

## Technologies

* Scala 3.8.3
* Spring Framework
* Spring Context
* ScalaTest 3.2.20
* SBT

## Goals

This project is intended to demonstrate:

* Inversion of Control (IoC)
* Spring bean lifecycle
* Bean customization techniques
* Testable Spring applications in Scala
* Integration between Scala and Spring Java libraries
