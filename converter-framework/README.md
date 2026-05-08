# Converter Framework

A complete Scala 3.8.3 project that implements a flexible and extensible converter framework capable of transforming complex domain objects into DTOs and other representations.

The project demonstrates:

* Generic type conversion
* Nested object transformation
* Registry-based converter resolution
* Complex object mapping
* Aggregation and formatting
* Inventory and total calculations
* Strong unit testing with ScalaTest
* Clean object-oriented and functional Scala design

---

# Features

* Convert complex domain objects into DTOs
* Support nested object conversion
* Generic converter abstraction
* Runtime converter registry
* Composite object transformation
* Total calculation and rounding support
* Immutable domain models
* High-volume conversion support
* Deep automated test coverage

---

# Technologies

* Scala 3.8.3
* SBT
* ScalaTest 3.2.20

---

# Project Structure

```text
src
├── main
│   └── scala
│       └── converter
│           ├── Address.scala
│           ├── AddressConverter.scala
│           ├── AddressDTO.scala
│           ├── Converter.scala
│           ├── ConverterRegistry.scala
│           ├── Order.scala
│           ├── OrderConverter.scala
│           ├── OrderDTO.scala
│           ├── Product.scala
│           ├── ProductConverter.scala
│           ├── ProductDTO.scala
│           ├── User.scala
│           ├── UserConverter.scala
│           └── UserDTO.scala
│
└── test
    └── scala
        └── converter
            └── ConverterFrameworkSpec.scala
```

---

# Architecture

The system is based on a generic converter abstraction:

```scala
trait Converter[A, B] {
  def convert(value: A): B
}
```

Each converter specializes the transformation between domain objects and DTOs.

Example:

* `Address -> AddressDTO`
* `User -> UserDTO`
* `Product -> ProductDTO`
* `Order -> OrderDTO`

The framework also includes a runtime registry capable of dynamically resolving converters.

---

# Domain Models

## Address

```scala
case class Address(
  street: String,
  city: String,
  country: String,
  zipCode: String
)
```

## User

```scala
case class User(
  id: String,
  firstName: String,
  lastName: String,
  age: Int,
  address: Address
)
```

## Product

```scala
case class Product(
  id: String,
  name: String,
  price: Double,
  quantity: Int
)
```

## Order

```scala
case class Order(
  id: String,
  user: User,
  products: List[Product]
)
```

---

# DTO Models

DTOs represent transformed and simplified output objects.

Example:

```scala
case class UserDTO(
  id: String,
  fullName: String,
  age: Int,
  address: AddressDTO
)
```

---

# Converter Registry

The registry allows dynamic registration and lookup of converters.

Example:

```scala
val registry = new ConverterRegistry

registry.register(
  classOf[Address],
  classOf[AddressDTO],
  new AddressConverter
)
```

Conversion:

```scala
val dto = registry.convert(
  address,
  classOf[Address],
  classOf[AddressDTO]
)
```

---

# Order Conversion

The order conversion process demonstrates:

* Nested conversion
* Product list mapping
* Total calculation
* Monetary rounding
* Composite DTO creation

Example output:

```scala
OrderDTO(
  id = "o1",
  customer = UserDTO(...),
  products = List(...),
  total = 2001.00
)
```

---

# Running Tests

Run all tests:

```bash
sbt test
```

Run a specific suite:

```bash
sbt "testOnly converter.ConverterFrameworkSpec"
```

---

# Test Coverage

The project contains deep test coverage for:

* Address conversion
* User conversion
* Product conversion
* Order conversion
* Registry registration
* Registry lookup
* Missing converter validation
* Rounding precision
* High-volume conversion
* Product order preservation
* Empty collections
* Large dataset handling
* Nested conversion correctness

---

# Example Usage

## Address Conversion

```scala
val converter = new AddressConverter

val dto = converter.convert(
  Address(
    "Main Street",
    "New York",
    "USA",
    "10001"
  )
)
```

## Product Conversion

```scala
val converter = new ProductConverter

val dto = converter.convert(
  Product(
    "p1",
    "Laptop",
    1000.50,
    2
  )
)
```

## Order Conversion

```scala
val orderConverter =
  new OrderConverter(
    new UserConverter(new AddressConverter),
    new ProductConverter
  )

val dto = orderConverter.convert(order)
```

---

# Design Principles

The project follows:

* Single Responsibility Principle
* Open/Closed Principle
* Composition over inheritance
* Immutable data modeling
* Type-safe conversion
* Explicit transformation logic

---

# Performance

The framework supports:

* High-volume conversions
* Efficient converter lookup
* Immutable transformation pipelines
* Low-memory DTO creation

The test suite validates conversions with hundreds and thousands of objects.

---

# Future Improvements

Possible future extensions:

* JSON serialization support
* Reflection-free converter generation
* Async conversion pipelines
* Streaming transformations
* Validation integration
* Bidirectional converters
* Automatic mapper generation
* Database entity converters

---

# Build

## Requirements

* JDK 21+
* SBT 1.10+

## Compile

```bash
sbt compile
```

## Test

```bash
sbt test
```

## Package

```bash
sbt package
```
