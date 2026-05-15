# Spring BeanPostProcessor (Scala 3)

A lightweight Scala project that demonstrates the internal lifecycle mechanics of the Spring Framework `BeanPostProcessor` system.

This project implements a simplified IoC container capable of:

* Registering beans
* Managing initialization lifecycle callbacks
* Executing pre-initialization hooks
* Executing post-initialization hooks
* Applying bean transformations dynamically
* Tracking initialized beans
* Simulating core container extension points

The implementation is intentionally minimal and educational, focusing on how bean lifecycle customization works internally inside dependency injection containers.

---

# Overview

Modern dependency injection frameworks such as Spring Framework allow developers to intercept and modify beans during their lifecycle using `BeanPostProcessor`.

This project recreates that behavior using plain Scala.

The container supports:

| Feature                     | Description                                      |
| --------------------------- | ------------------------------------------------ |
| Before Initialization Hooks | Modify beans before lifecycle initialization     |
| After Initialization Hooks  | Execute logic after initialization               |
| Lifecycle Contracts         | Automatic invocation of initialization callbacks |
| Bean Tracking               | Monitor initialized beans                        |
| Dynamic Bean Mutation       | Alter bean state during processing               |
| Extensible Processors       | Add unlimited custom post-processors             |

---

# Project Structure

```text id="i3j7pk"
spring-bpp/
│
├── build.sbt
│
├── src/
│   ├── main/
│   │   └── scala/
│   │       ├── BeanPostProcessor.scala
│   │       ├── Initializable.scala
│   │       ├── GreetingService.scala
│   │       ├── PrefixValidationPostProcessor.scala
│   │       ├── InitializationTrackingPostProcessor.scala
│   │       └── Container.scala
│   │
│   └── test/
│       └── scala/
│           └── BeanPostProcessorTest.scala
```

---

# Technologies Used

* Scala 3.3.3
* SBT
* ScalaTest
* Java Collections Framework

---

# Core Concepts

## BeanPostProcessor

Defines extension points for intercepting beans during their lifecycle.

```scala id="l6d2vp"
trait BeanPostProcessor {

  def postProcessBeforeInitialization(
    bean: AnyRef,
    beanName: String
  ): AnyRef = {
    bean
  }

  def postProcessAfterInitialization(
    bean: AnyRef,
    beanName: String
  ): AnyRef = {
    bean
  }

}
```

This mirrors the behavior of Spring's bean lifecycle interception model.

---

# Initializable Contract

Beans implementing `Initializable` participate in container lifecycle callbacks.

```scala id="p9v4ke"
trait Initializable {
  def initialize(): Unit
  def isInitialized: Boolean
}
```

The container automatically invokes:

```scala id="m5s8qo"
initialize()
```

during bean resolution.

---

# GreetingService

A simple service bean used throughout the lifecycle demonstrations.

Features:

* Mutable configuration
* Initialization tracking
* Runtime message generation

Example:

```scala id="z7q1bw"
def greet(name: String): String = {
  s"${prefix}${name}"
}
```

---

# PrefixValidationPostProcessor

This processor validates and modifies bean configuration before initialization.

Behavior:

* Checks whether `GreetingService` has a valid prefix
* Automatically assigns a default value if missing

Example default behavior:

```scala id="f2r9xn"
service.setPrefix("Hello, ")
```

This demonstrates how frameworks can inject defaults dynamically during container startup.

---

# InitializationTrackingPostProcessor

Tracks which beans successfully completed initialization.

Behavior:

* Executes after initialization
* Verifies lifecycle completion
* Registers initialized bean names internally

Example:

```scala id="k0t8am"
if (initBean.isInitialized) {
  trackedBeans.add(beanName)
}
```

This simulates monitoring and lifecycle auditing mechanisms commonly used in enterprise containers.

---

# Container Lifecycle Flow

The custom container executes the following sequence:

```text id="x8w4hs"
Bean Retrieval
      ↓
Before Initialization Processors
      ↓
Lifecycle Initialization Callback
      ↓
After Initialization Processors
      ↓
Fully Processed Bean Returned
```

---

# Container Implementation

The container manages:

* Bean storage
* Processor registration
* Lifecycle execution
* Hook orchestration

Core processing flow:

```scala id="n3u7dl"
val beforeIterator = processors.iterator()

while (beforeIterator.hasNext) {
  currentBean =
    beforeIterator.next()
      .postProcessBeforeInitialization(
        currentBean,
        name
      )
}
```

Initialization execution:

```scala id="v6y2op"
currentBean match {
  case initBean: Initializable => {
    initBean.initialize()
  }
  case _ => {}
}
```

After-initialization execution:

```scala id="e9r5qa"
val afterIterator = processors.iterator()

while (afterIterator.hasNext) {
  currentBean =
    afterIterator.next()
      .postProcessAfterInitialization(
        currentBean,
        name
      )
}
```

---

# Running the Project

## Clone the Repository

```bash id="d1q8tk"
git clone https://github.com/your-username/spring-bpp.git
cd spring-bpp
```

---

## Run Tests

```bash id="w2c9je"
sbt test
```

---

# Test Coverage

The automated tests validate:

* Before-initialization bean mutation
* Preservation of custom bean state
* Lifecycle callback execution
* After-initialization tracking
* Exception handling for missing beans

Example validation:

```scala id="h7k1vf"
resolved.greet("Sarah")
shouldBe "Hello, Sarah"
```

Initialization tracking validation:

```scala id="u5z0mn"
tracker.isTracked("trackedService")
shouldBe true
```

---

# Educational Goals

This project helps developers understand:

* Bean lifecycle management
* Container extension points
* Initialization hooks
* Runtime bean mutation
* Interceptor pipelines
* IoC container internals
* Post-processing orchestration
* Lifecycle callback patterns

---

# Design Advantages

## Lightweight Architecture

No external dependency injection framework is required.

## Extensible Processing Pipeline

Multiple post-processors can be chained together.

## Lifecycle Awareness

Beans participate in explicit initialization phases.

## Educational Simplicity

The implementation focuses entirely on lifecycle mechanics.

---

# Limitations

## No Dependency Injection

Beans are manually registered.

## No Reflection-Based Wiring

Dependencies are not automatically resolved.

## No Scope Management

Only basic singleton-like behavior exists.

## No Proxy Generation

Beans are not dynamically wrapped or proxied.

## No Annotation Support

Lifecycle configuration is fully programmatic.

---

# Possible Future Improvements

* Constructor dependency injection
* Annotation-based processing
* Bean scopes
* Reflection-based auto-wiring
* Proxy generation
* Lazy initialization
* Circular dependency detection
* Bean destruction callbacks
* AOP-style interceptors
* Configuration files
