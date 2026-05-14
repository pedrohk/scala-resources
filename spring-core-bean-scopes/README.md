# Spring Scopes

A lightweight Scala project that demonstrates the concept of **Singleton** and **Prototype** bean scopes inspired by the behavior of the Spring Framework IoC container.

This project implements a simple custom `BeanFactory` capable of:

* Registering singleton beans
* Registering prototype beans
* Returning shared or fresh instances depending on scope
* Managing bean providers dynamically
* Throwing exceptions for missing beans
* Validating behavior through automated tests using ScalaTest

---

# Overview

Dependency Injection frameworks such as Spring Framework commonly support different bean scopes.

This project recreates two of the most important scopes in a minimal and educational way:

| Scope     | Behavior                                          |
| --------- | ------------------------------------------------- |
| Singleton | The same object instance is returned every time   |
| Prototype | A new object instance is created on every request |

The implementation is intentionally simple to help developers understand the internal mechanics behind IoC containers and bean lifecycle management.

---

# Project Structure

```text
spring-scopes-demo/
│
├── build.sbt
│
├── src/
│   ├── main/
│   │   └── scala/
│   │       ├── Bean.scala
│   │       └── BeanFactory.scala
│   │
│   └── test/
│       └── scala/
│           └── BeanFactoryTest.scala
```

---

# Technologies Used

* Scala 3.3.3
* SBT
* Java ConcurrentHashMap
* ScalaTest

---

# Features

## Singleton Bean Registration

Singleton beans are stored in an internal registry and reused across all calls.

```scala
factory.registerSingleton(
  "mySingleton",
  new SingletonBean("Shared instance")
)
```

Every request returns the exact same object reference.

---

## Prototype Bean Registration

Prototype beans are registered as providers (factory functions).

```scala
factory.registerPrototype(
  "myPrototype",
  () => new PrototypeBean("New instance every time")
)
```

Each call creates a completely new instance.

---

## Thread-Safe Internal Storage

The project uses:

```scala
java.util.concurrent.ConcurrentHashMap
```

This ensures safe concurrent access to:

* Singleton registry
* Prototype providers

---

## UUID-Based Instance Tracking

Each bean receives a unique UUID:

```scala
val instanceId: String =
  java.util.UUID.randomUUID().toString
```

This makes it easy to verify whether instances are shared or recreated.

---

# BeanFactory Design

The `BeanFactory` acts as a tiny IoC container.

Responsibilities include:

* Managing bean registrations
* Resolving dependencies by name
* Handling singleton lifecycle
* Handling prototype instantiation
* Throwing exceptions for invalid lookups

Core retrieval logic:

```scala
def getBean(name: String): Bean = {
  if (singletonRegistry.containsKey(name)) {
    singletonRegistry.get(name)
  } else if (providers.containsKey(name)) {
    providers.get(name)()
  } else {
    throw new NoSuchElementException(
      s"No bean registered under name: $name"
    )
  }
}
```

---

# Running the Project

## Clone the Repository

```bash
git clone https://github.com/your-username/spring-scopes-demo.git
cd spring-scopes-demo
```

---

## Run Tests

```bash
sbt test
```

---

# Test Coverage

The test suite validates:

* Singleton instance reuse
* Prototype instance recreation
* Exception handling for missing beans

Example assertion:

```scala
firstCall should be theSameInstanceAs secondCall
```

Prototype validation:

```scala
firstCall shouldNot be theSameInstanceAs secondCall
```

---

# Example Output Concept

Singleton:

```text
Request 1 -> instanceId: abc123
Request 2 -> instanceId: abc123
```

Prototype:

```text
Request 1 -> instanceId: abc123
Request 2 -> instanceId: xyz789
```

---

# Tradeoffs and Design Decisions

## Advantages

### Simple and Educational

The project focuses on core concepts without framework complexity.

### Thread-Safe Registries

Using `ConcurrentHashMap` improves concurrency safety.

### Flexible Prototype Providers

Prototype beans are lazily created using functions.

### Lightweight

No external DI container is required.

---

## Limitations

### No Dependency Injection

Beans are manually instantiated and registered.

### No Lifecycle Hooks

Features such as:

* initialization callbacks
* destruction hooks
* post processors

are not implemented.

### No Scope Hierarchy

Only singleton and prototype scopes exist.

### String-Based Lookup

Bean resolution is name-based instead of type-safe.

---

# Learning Goals

This project is useful for understanding:

* IoC container internals
* Bean scopes
* Object lifecycle management
* Factory patterns
* Lazy instantiation
* Thread-safe registries
* Functional providers in Scala

---

# Possible Future Improvements

* Constructor dependency injection
* Type-safe bean lookup
* Annotation-based registration
* Bean post-processors
* Custom scopes
* Circular dependency detection
* Reflection-based auto-wiring
* Configuration files
* Bean lifecycle callbacks
