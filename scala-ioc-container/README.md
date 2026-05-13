# Spring Core - IoC

A lightweight Spring-inspired IoC (Inversion of Control) container built in Scala 3.8.3.

This project demonstrates the internal concepts behind dependency injection frameworks such as Spring Core, including bean registration, dependency resolution, constructor injection, singleton lifecycle management, bean definitions, and application context orchestration.

The framework was implemented entirely from scratch without using external DI libraries.

---

# Features

* Custom IoC container
* Bean definitions
* Constructor-based dependency injection
* `@Component` annotation support
* `@Inject` annotation support
* Singleton bean lifecycle
* Recursive dependency resolution
* Runtime bean lookup
* Bean existence validation
* Bean definition registry
* Deep ScalaTest coverage
* Pure Scala 3.8.3 implementation

---

# Technologies

* Scala 3.8.3
* SBT
* ScalaTest 3.2.19

---

# Project Structure

```text id="drm9eh"
src
├── main
│   └── scala
│       └── ioc
│           ├── ApplicationContext.scala
│           ├── BeanDefinition.scala
│           ├── Component.scala
│           ├── Inject.scala
│           ├── PlainClass.scala
│           ├── UserRepository.scala
│           ├── UserService.scala
│           ├── NotificationService.scala
│           └── ApplicationService.scala
│
└── test
    └── scala
        └── ioc
            └── ApplicationContextSpec.scala
```

---

# Core Classes

## ApplicationContext

The central container responsible for:

* Bean registration
* Bean creation
* Dependency injection
* Singleton management
* Dependency graph resolution

Example:

```scala id="r3zl98"
val context = new ApplicationContext(
  Seq(
    classOf[UserRepository],
    classOf[UserService],
    classOf[NotificationService],
    classOf[ApplicationService]
  )
)
```

---

## BeanDefinition

Represents metadata about a bean registered in the container.

Responsibilities include:

* Bean type storage
* Bean naming
* Singleton metadata
* Bean instance tracking

Example structure:

```scala id="v7z9sh"
case class BeanDefinition(
  name: String,
  beanClass: Class[?],
  singleton: Boolean
)
```

---

## Component

Custom annotation used to mark classes as managed beans.

```scala id="v2q5zi"
@Component
class UserService
```

The container recognizes these classes as injectable components.

---

## Inject

Annotation used to mark constructor or dependency injection points.

```scala id="njlwm4"
class UserService @Inject() (
  val repository: UserRepository
)
```

---

## PlainClass

A regular class intentionally not managed by the container.

Used to validate:

* Non-managed object behavior
* Bean filtering
* Container isolation rules

Example:

```scala id="yr3n7e"
class PlainClass {

  def value(): String = {
    "plain"
  }
}
```

---

## UserRepository

Simulates a persistence layer.

Responsibilities:

* Data access simulation
* User retrieval
* Existence validation

---

## UserService

Business service layer.

Responsibilities:

* User operations
* Delegating persistence logic
* Dependency injection usage

---

## NotificationService

Handles notification-related behavior.

Responsibilities:

* Message delivery simulation
* Notification orchestration

---

## ApplicationService

Top-level orchestration service.

Responsibilities:

* Coordinating application flow
* Using multiple injected services
* Demonstrating nested dependency graphs

---

# Architecture Overview

The framework mimics a simplified Spring container architecture.

```text id="g1k8iz"
ApplicationContext
        │
        ├── BeanDefinition Registry
        │
        ├── UserRepository
        │
        ├── UserService
        │       │
        │       └── UserRepository
        │
        ├── NotificationService
        │
        └── ApplicationService
                │
                ├── UserService
                │       │
                │       └── UserRepository
                │
                └── NotificationService
```

---

# Dependency Injection

Dependencies are resolved recursively using constructor injection.

```scala id="n4n10f"
class ApplicationService(
  val userService: UserService,
  val notificationService: NotificationService
)
```

The container automatically creates and injects dependencies during bean instantiation.

---

# Singleton Lifecycle

All beans are singleton-scoped.

The container creates only one instance of each bean and reuses it across the application lifecycle.

Example:

```scala id="g9n9sa"
val service1 =
  context.getBean(classOf[UserService])

val service2 =
  context.getBean(classOf[UserService])

assert(service1 eq service2)
```

---

# Example Usage

## Creating the Context

```scala id="n8qtq3"
val context = new ApplicationContext(
  Seq(
    classOf[UserRepository],
    classOf[UserService],
    classOf[NotificationService],
    classOf[ApplicationService]
  )
)
```

---

## Resolving a Bean

```scala id="3g4mvx"
val applicationService =
  context.getBean(classOf[ApplicationService])
```

---

## Executing Application Logic

```scala id="79j6ng"
val result =
  applicationService.process(10)

println(result)
```

Output:

```text id="2nax2w"
sent:user-10
```

---

# Running the Project

## Compile

```bash id="2u4u8v"
sbt compile
```

---

## Run Tests

```bash id="gg3z2y"
sbt test
```

---

# Test Coverage

The test suite validates:

* Bean registration
* Bean definition creation
* Bean existence checks
* Constructor injection
* Nested dependency resolution
* Singleton reuse
* Bean definition names
* Bean counts
* Recursive dependency graphs
* Runtime dependency injection
* Service behavior
* Exception handling
* Plain class exclusion
* Annotation behavior

---

# Core Concepts Demonstrated

## Inversion of Control (IoC)

Object creation is delegated to the container instead of manual instantiation.

---

## Dependency Injection (DI)

Dependencies are automatically resolved and injected through constructors.

---

## Bean Lifecycle Management

The container manages creation and reuse of singleton objects.

---

## Reflection-Based Instantiation

The framework uses Java reflection to:

* Discover constructors
* Resolve dependencies
* Instantiate objects dynamically

---

# Advantages

* Lightweight architecture
* Educational implementation
* Predictable dependency lifecycle
* Simple recursive dependency resolution
* Strong separation of concerns
* Easy to extend
* Pure Scala implementation

---

# Trade-Offs

## Pros

* Minimal and fast
* Transparent internals
* No external framework dependency
* Excellent for learning IoC concepts
* Deterministic singleton management

## Cons

* No automatic package scanning
* No circular dependency handling
* No lazy initialization
* No advanced bean scopes
* Reflection has runtime overhead
* No proxy-based AOP
* Limited compared to real Spring Core

---

# Future Improvements

* Automatic package scanning
* Prototype scope
* Lazy beans
* Circular dependency detection
* Bean post-processors
* Configuration classes
* Proxy generation
* Aspect-oriented programming
* Bean qualifiers
* Conditional beans

MIT License.
