# Spring Injection (Scala 3)

A lightweight Scala project that demonstrates the core concepts of **Dependency Injection** inspired by the Spring Framework ecosystem.

This project focuses on two common injection strategies:

* Constructor Injection
* Setter Injection

It provides a simple and educational implementation using plain Scala classes without relying on a full IoC container.

---

# Overview

Dependency Injection (DI) is a design pattern that helps reduce coupling between components by externalizing dependency creation and management.

This project demonstrates how different injection techniques work internally by implementing:

* Service abstractions using traits
* Multiple service implementations
* Controllers that receive dependencies externally
* Runtime dependency swapping
* Validation through automated tests using ScalaTest

---

# Project Structure

```text id="n2f7ks"
spring-injection/
│
├── build.sbt
│
├── src/
│   ├── main/
│   │   └── scala/
│   │       ├── MessageService.scala
│   │       ├── ConstructorInjectedController.scala
|   |       ├── EmailService.scala
|   |       ├── SmsService.scala
│   │       └── SetterInjectedController.scala
│   │
│   └── test/
│       └── scala/
│           └── InjectionTest.scala
```

---

# Technologies Used

* Scala 3.8.3
* SBT
* ScalaTest

---

# Implemented Concepts

| Concept                     | Description                                                          |
| --------------------------- | -------------------------------------------------------------------- |
| Constructor Injection       | Dependencies are required during object creation                     |
| Setter Injection            | Dependencies are assigned after object instantiation                 |
| Abstraction via Traits      | Controllers depend on interfaces instead of concrete implementations |
| Loose Coupling              | Services can be replaced without changing controller logic           |
| Runtime Dependency Swapping | Dependencies can be changed dynamically                              |

---

# Message Services

The project defines a common abstraction:

```scala id="7e1vpm"
trait MessageService {
  def getMessage: String
}
```

Two concrete implementations are provided:

## Email Service

```scala id="k2l8vd"
class EmailService extends MessageService {
  def getMessage: String = {
    "Email Service Message"
  }
}
```

## SMS Service

```scala id="bd5z0k"
class SmsService extends MessageService {
  def getMessage: String = {
    "SMS Service Message"
  }
}
```

---

# Constructor Injection

The dependency is required when creating the controller instance.

```scala id="0f3xjv"
val emailService = new EmailService()
val controller =
  new ConstructorInjectedController(emailService)
```

Implementation:

```scala id="r8tw9o"
class ConstructorInjectedController(
  private val messageService: MessageService
) {

  def processMessage: String = {
    messageService.getMessage
  }

}
```

## Advantages

* Immutable dependencies
* Safer initialization
* Easier testing
* Prevents partially constructed objects

---

# Setter Injection

Dependencies are assigned after object creation.

```scala id="g0s1af"
val controller = new SetterInjectedController()

controller.setMessageService(new SmsService())
```

Implementation:

```scala id="n9c2hm"
def setMessageService(service: MessageService): Unit = {
  this.messageService = service
}
```

## Advantages

* Flexible configuration
* Runtime dependency replacement
* Optional dependency support

## Risks

* Objects may become invalid before initialization
* Requires runtime validation

This project demonstrates that risk explicitly through:

```scala id="4w8vut"
throw new IllegalStateException(
  "Dependency 'messageService' has not been initialized via setter injection."
)
```

---

# Running the Project

## Clone the Repository

```bash id="3s7dqp"
git clone https://github.com/your-username/spring-injection.git
cd spring-injection
```

---

## Run Tests

```bash id="w1q7nx"
sbt test
```

---

# Test Coverage

The automated test suite validates:

* Successful constructor injection
* Successful setter injection
* Runtime validation for missing dependencies
* Dynamic dependency replacement

Example test:

```scala id="1y4vzs"
controller.processMessage shouldBe
  "SMS Service Message"
```

Validation failure example:

```scala id="x7nqle"
assertThrows[IllegalStateException] {
  controller.processMessage
}
```

---

# Example Flow

## Constructor Injection

```text id="3kv4nu"
Controller Creation
        ↓
Dependency Provided
        ↓
Controller Ready To Use
```

---

## Setter Injection

```text id="gw9fmr"
Controller Creation
        ↓
Controller Exists Without Dependency
        ↓
Dependency Injected Later
        ↓
Controller Ready To Use
```

---

# Design Goals

This project was built to help developers understand:

* The fundamentals of Dependency Injection
* The difference between constructor and setter injection
* Loose coupling principles
* Interface-based design
* Runtime dependency management
* Testable architecture patterns

---

# Tradeoffs Between Injection Styles

| Constructor Injection                 | Setter Injection                 |
| ------------------------------------- | -------------------------------- |
| Immutable dependencies                | Mutable dependencies             |
| Safer initialization                  | More flexible                    |
| Easier to reason about                | Allows runtime replacement       |
| Recommended for required dependencies | Useful for optional dependencies |

---

# Possible Future Improvements

* Field injection examples
* Simple IoC container
* Annotation-based dependency registration
* Automatic dependency resolution
* Bean lifecycle management
* Circular dependency detection
* Configuration-driven injection
* Reflection-based wiring
