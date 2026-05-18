# Spring Value Injection (Scala)

A lightweight educational project that demonstrates how the `@Value` annotation mechanism works internally in frameworks like [Spring Framework](https://spring.io/projects/spring-framework?utm_source=chatgpt.com).

This project implements a simple property injection system in Scala using Java annotations, reflection, and placeholder resolution with support for:

* Property injection into fields
* Default values
* Static literal values
* Runtime reflection processing
* Placeholder parsing (`${key:default}`)
* Unit testing with ScalaTest

---

# Features

* Custom `@Value` annotation
* Reflection-based field injection
* Property placeholder resolution
* Default fallback values
* Exception handling for unresolved placeholders
* Simple and clean architecture
* Scala 3 compatible
* Unit tested

---

# Project Structure

```text
spring-value/
│
├── build.sbt
│
├── src/
│   ├── main/
│   │   └── scala/
│   │       ├── Value.java
│   │       ├── PropertyResolver.scala
│   │       ├── AppConfig.scala
│   │       └── ValueAnnotationProcessor.scala
│   │
│   └── test/
│       └── scala/
│           └── ValueAnnotationTest.scala
```

---

# How It Works

The project simulates a very small portion of dependency injection frameworks.

The `ValueAnnotationProcessor` scans object fields using reflection and looks for fields annotated with `@Value`.

Example:

```scala
@Value("${app.name:DefaultApp}")
private var appName: String = ""
```

The processor resolves the placeholder expression using `PropertyResolver` and injects the final value into the field.

---

# Placeholder Syntax

## Basic Placeholder

```text
${property.key}
```

Example:

```scala
@Value("${app.version}")
```

---

## Placeholder With Default Value

```text
${property.key:defaultValue}
```

Example:

```scala
@Value("${app.timeout:5000}")
```

If the property does not exist, the default value is used.

---

## Static Literal Values

```scala
@Value("StaticLiteralValue")
```

Non-placeholder values are injected directly.

---

# Core Components

## `Value.java`

Defines the custom runtime annotation used for field injection.

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Value {
    String value();
}
```

---

## `PropertyResolver.scala`

Responsible for resolving property expressions.

Supported behaviors:

* Resolve existing properties
* Use default values when provided
* Throw exception when required property is missing

---

## `ValueAnnotationProcessor.scala`

Processes annotated fields using Java reflection.

Responsibilities:

* Scan fields
* Detect `@Value`
* Resolve expressions
* Inject values dynamically

---

## `AppConfig.scala`

Example configuration class containing injected fields.

---

# Running the Tests

This project uses [ScalaTest](https://www.scalatest.org/?utm_source=chatgpt.com).

Run:

```bash
sbt test
```

---

# Example

## Properties

```scala
val props = new java.util.HashMap[String, String]()
props.put("app.version", "1.0.2")
```

## Processing

```scala
val resolver = new PropertyResolver(props)
val processor = new ValueAnnotationProcessor(resolver)

val config = new AppConfig()

processor.process(config)
```

## Result

```text
appName      -> DefaultApp
appVersion   -> 1.0.2
timeout      -> 5000
staticValue  -> StaticLiteralValue
```

---

# Test Coverage

The test suite validates:

* Successful property injection
* Default value resolution
* Static literal injection
* Property overriding
* Exception handling for missing required placeholders

---

# Technologies Used

* Scala 3.8.3
* Java Annotations
* Java Reflection API
* ScalaTest
* SBT
  
---

# Future Improvements

Possible enhancements:

* Type conversion support (`Int`, `Boolean`, etc.)
* Nested property resolution
* Environment variable support
* YAML / properties file loading
* Constructor injection
* Bean container integration
* Recursive object processing
