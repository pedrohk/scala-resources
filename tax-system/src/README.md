# Tax System (Scala 3)

## Overview

This project implements a flexible TAX system where different products are taxed based on their category, state, and year. It demonstrates a clean architecture using Scala 3, with a focus on correctness, testability, and simplicity.

The system allows you to:

* Define products with categories
* Define tax rules per state and year
* Calculate tax amounts
* Calculate total price including tax

## Project Structure

```
tax-system/
├── build.sbt
├── src/
│   ├── main/
│   │   └── scala/
│   │       └── tax/
│   │           ├── model.scala
│   │           ├── repository.scala
│   │           └── service.scala
│   └── test/
│       └── scala/
│           └── tax/
│               └── TaxServiceSpec.scala
```

## Core Concepts

### Product

Represents an item with a name and category.

### State

Represents a geographical state using a code.

### TaxRule

Defines the tax rate for:

* Product category
* State
* Year

### TaxRepository

Abstracts how tax rules are retrieved.

### InMemoryTaxRepository

Stores tax rules in memory and retrieves them efficiently using indexing.

### TaxService

Responsible for:

* Calculating tax based on rules
* Calculating total price including tax

## How It Works

1. A product has a category.
2. A tax rule is defined for a category, state, and year.
3. The repository finds the correct tax rate.
4. The service applies the rate to the product price.

If no rule is found, the system throws an exception.

## Example

```
Product("Laptop", "electronics")
State("RS")
Year: 2024
Price: 100

Tax Rate: 20%
Tax: 20
Total: 120
```

## Running the Project

### Requirements

* Java 17+
* sbt installed

### Compile

```
sbt compile
```

### Run Tests

```
sbt test
```

## Testing

The project includes comprehensive tests covering:

* Multiple product categories
* Different states
* Multiple years
* Precision and rounding
* Edge cases (zero price, missing rules, large values)

All tests are designed to pass and validate the correctness of the system.

## Design Decisions

### In-Memory Repository

Chosen for simplicity and speed. It can be replaced by a database-backed implementation without changing the service layer.

### Strong Typing

Using case classes ensures immutability and clarity.

### Explicit Error Handling

The system fails fast when a tax rule is missing.

### Precision Handling

BigDecimal is used for financial calculations to avoid floating-point errors.

## Possible Improvements

* Add database persistence
* Support tax exemptions
* Add multi-currency support
* Implement caching
* Add REST API layer
* Support progressive or compound taxes
