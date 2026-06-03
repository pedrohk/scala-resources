# Spring Core - Bean Scopes (Singleton, Prototype)

A Scala 3.8.3 project built with Spring Framework that demonstrates how Spring's IoC container manages bean lifecycles using different scopes.

This project focuses on practical usage of:

- Singleton scope
- Prototype scope
- Dependency Injection
- Bean lifecycle management
- Spring Java libraries with Scala
- Automated testing with ScalaTest

The implementation intentionally keeps the domain small so the behavior of bean scopes remains easy to observe and validate.

---

# Project Structure

```text
spring-core-bean-scopes
│
├── build.sbt
│
├── src
│   ├── main
│   │   └── scala
│   │       └── pedrohk
│   │           └── beanscope
│   │               ├── Application.scala
│   │               │
│   │               ├── config
│   │               │   └── BeanScopeConfiguration.scala
│   │               │
│   │               ├── model
│   │               │   ├── DeveloperWorkspace.scala
│   │               │   └── ScopeSnapshot.scala
│   │               │
│   │               └── service
│   │                   ├── SingletonWorkspaceService.scala
│   │                   └── PrototypeSessionService.scala
│   │
│   └── test
│       └── scala
│           └── pedrohk
│               └── beanscope
│                   ├── ApplicationTest.scala
│                   ├── config
│                   ├── model
│                   └── service
```

---

# Technologies

| Technology | Version |
|---|---|
| Scala | 3.8.3 |
| Spring Framework | 6.x |
| Spring Context | Included |
| ScalaTest | 3.2.20 |
| SBT | Latest compatible |

---

# Concepts Demonstrated

## Singleton Scope

Beans created with singleton scope are instantiated once and reused throughout the application lifecycle.

Example in this project:

```text
DeveloperWorkspace
SingletonWorkspaceService
```

Behavior:

```text
Request 1 → same instance
Request 2 → same instance
Request 3 → same instance
```

---

## Prototype Scope

Prototype beans generate a new object every time they are requested from the container.

Example in this project:

```text
ScopeSnapshot
PrototypeSessionService
```

Behavior:

```text
Request 1 → new instance
Request 2 → new instance
Request 3 → new instance
```

---

# Application Flow

```text
Spring Context
      │
      ├── Singleton DeveloperWorkspace
      │
      └── Prototype ScopeSnapshot
                │
                ▼
      PrototypeSessionService
```

The application creates a reusable workspace bean while generating independent session snapshots for prototype requests.

---

# Running the Project

## Compile

```bash
sbt compile
```

## Run

```bash
sbt run
```

---

# Running Tests

Execute the complete test suite:

```bash
sbt test
```

Execute a single test:

```bash
sbt "testOnly pedrohk.beanscope.config.BeanScopeConfigurationTest"
```

---

# Test Coverage

The test suite validates:

- Model construction
- Service behavior
- Singleton scope reuse
- Prototype scope recreation
- Bean injection
- Spring container initialization
- Application bootstrap

Tests use:

```text
ScalaTest 3.2.20
```

No mocking framework is required.

---

# Example Scope Behavior

Singleton:

```text
workspaceA == workspaceB
true
```

Prototype:

```text
snapshotA == snapshotB
false
```

---

# Design Notes

This project follows a lightweight architecture:

```text
Configuration
    ↓
Models
    ↓
Services
    ↓
Tests
```

The objective is to demonstrate Spring bean scope behavior clearly while keeping the implementation fully compatible with Scala 3.8.3.

---

# Build

```bash
sbt clean compile test
```
