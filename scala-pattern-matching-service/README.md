# Scala Pattern Matching Service

A backend service written in **Scala 3.8.4** designed to demonstrate advanced usage of **Pattern Matching** and **For Comprehension**, including comparisons against equivalent functional compositions using `map` and `flatMap`.

The project follows a layered backend architecture and includes extensive automated tests.

---

# Objectives

This project demonstrates:

* Modern Scala 3 backend organization
* Advanced Pattern Matching techniques
* Functional programming concepts
* For Comprehension
* Composition with `map` and `flatMap`
* Domain modeling using `sealed traits`
* Scala enums
* Extractors using `unapply`
* Error handling with `Either`
* Optional values with `Option`
* Unit testing with ScalaTest

---

# Technology Stack

| Technology | Version |
| ---------- | ------- |
| Scala      | 3.8.4   |
| SBT        | Latest  |
| ScalaTest  | 3.2.19  |

---

# Project Structure

```text
scala-pattern-matching-service/
├── build.sbt
├── src
│   ├── main
│   │   └── scala
│   │       └── orderbackend
│   │           ├── domain
│   │           │   ├── Customer.scala
│   │           │   ├── Payment.scala
│   │           │   └── Order.scala
│   │           ├── repository
│   │           │   └── OrderRepository.scala
│   │           ├── service
│   │           │   ├── PatternMatchingService.scala
│   │           │   ├── ForComprehensionService.scala
│   │           │   └── OrderService.scala
│   │           └── Main.scala
│   └── test
│       └── scala
│           └── orderbackend
│               ├── PatternMatchingServiceTest.scala
│               └── ForComprehensionServiceTest.scala
```

---

# Architecture

The application is divided into layers.

## Domain Layer

Contains business entities and rules.

### Customer

Represents a customer.

Attributes:

* id
* name
* vip

### Payment

Implemented as a Scala enum.

Supported methods:

* Pix
* CreditCard
* DebitCard
* Cash

### Order

Represents a business order.

Attributes:

* id
* customer
* items
* amount
* payment
* status

Order states are modeled using a sealed hierarchy.

---

## Repository Layer

Responsible for data access.

Current implementation:

```text
InMemoryOrderRepository
```

Provides:

```scala
find(id): Option[Order]
```

Demonstrates:

* Option
* Immutable collections
* Functional lookup

---

## Service Layer

Contains business operations and language demonstrations.

### PatternMatchingService

Central service for Pattern Matching examples.

Implemented patterns:

### 1. Case Class Matching

```scala
case Order(...)
```

Extract values directly from domain objects.

---

### 2. Nested Pattern Matching

```scala
case Order(_, Customer(...), ...)
```

Match deeply nested structures.

---

### 3. Guard Conditions

```scala
case value if value > threshold
```

Apply additional runtime conditions.

---

### 4. Extractor Pattern

Custom extractor:

```scala
object HighValue
```

Uses:

```scala
unapply()
```

Allows custom matching rules.

Example:

```scala
case HighValue(amount)
```

---

### 5. List Pattern Matching

Examples:

```scala
case Nil
case head :: Nil
```

Detect:

* empty lists
* single-element lists

---

### 6. Enum Matching

Example:

```scala
case Payment.Pix
```

Demonstrates exhaustive matching.

---

### 7. Tuple Matching

Example:

```scala
case ("admin", level)
```

Pattern decomposition.

---

### 8. Typed Matching

Example:

```scala
case s: String
case i: Int
```

Runtime type-based branching.

---

### 9. Option Matching

Example:

```scala
case Some(value)
case None
```

Explicit optional value handling.

---

## ForComprehensionService

Demonstrates two equivalent functional styles.

### Style 1 — For Comprehension

```scala
for
  order <- repository.find(id).toRight("not-found")
  validated <- Either.cond(...)
yield result
```

Characteristics:

* sequential
* readable
* imperative appearance
* preferred for long pipelines

---

### Style 2 — flatMap / map

```scala
repository
  .find(id)
  .toRight("not-found")
  .flatMap(...)
```

Characteristics:

* explicit
* lower-level
* shorter for simple chains

---

# Pattern Matching vs For Comprehension

These concepts solve different problems.

## Pattern Matching

Purpose:

```text
Inspect and destructure data
```

Example:

```scala
value match
```

Use cases:

* branching
* parsing
* business rules
* domain decomposition

---

## For Comprehension

Purpose:

```text
Compose computations
```

Example:

```scala
for
  x <- option
yield x
```

Use cases:

* chaining
* validation
* async flows
* Either/Option/Future pipelines

---

# Testing

The project includes deep automated tests.

Covered scenarios:

### PatternMatchingServiceTest

* VIP customer detection
* Enterprise order classification
* Tuple decomposition
* Runtime typed matching
* Option handling

### ForComprehensionServiceTest

* successful retrieval
* flatMap equivalence
* invalid order handling
* not-found scenarios

---

# Running the Project

Compile:

```bash
sbt compile
```

Run:

```bash
sbt run
```

Execute tests:

```bash
sbt test
```

Clean:

```bash
sbt clean
```

---

# Expected Output

Example:

```text
Right(1:3000)
Right(1:3000)
```

---

# Key Scala Concepts Demonstrated

* Scala 3 syntax
* Enums
* Sealed traits
* Case classes
* Extractors
* Pattern matching
* For comprehension
* map
* flatMap
* Either
* Option
* Functional composition
* Immutable collections
* Unit testing
