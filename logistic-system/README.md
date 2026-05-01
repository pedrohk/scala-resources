# Logistic System (Scala 3)

## Overview

This project implements a dynamic freight pricing system using Scala 3. It calculates shipping costs based on shipment characteristics such as weight, volume, and transport type, while also supporting pricing variations by year.

The system is designed to be simple, extensible, and deterministic, with a strong focus on correctness and test coverage.

## Features

* Freight calculation based on weight and volume
* Support for multiple transport types (Truck, Ship, Rail)
* Dynamic pricing rules per year
* Pluggable pricing repository
* Deterministic rounding for financial calculations
* Comprehensive test suite

## Project Structure

```id="4j0u3o"
logistic-system/
├── build.sbt
├── src/
│   ├── main/
│   │   └── scala/logistic/
│   │       ├── model.scala
│   │       ├── pricing.scala
│   │       └── service.scala
│   └── test/
│       └── scala/logistic/
│           └── FreightServiceSpec.scala
```

## Core Concepts

### TransportType

Defines the type of transportation used:

* TRUCK
* SHIP
* RAIL

### Dimensions

Represents the physical size of a shipment and provides volume calculation:

```id="z2gqk4"
volume = length * width * height
```

### Shipment

Encapsulates:

* Weight
* Dimensions
* Transport type

### PricingRule

Defines how freight is calculated for a specific:

* Transport type
* Year

Each rule contains:

* Base rate
* Weight factor
* Volume factor

### PricingRepository

Abstracts access to pricing rules.

### InMemoryPricingRepository

Stores pricing rules in memory and provides fast lookup using indexed mapping.

### FreightService

Responsible for calculating freight cost using:

```id="5lmxt4"
total = baseRate + (weight * weightFactor) + (volume * volumeFactor)
```

Results are rounded to two decimal places using HALF_UP strategy.

## How It Works

1. A shipment is created with weight, dimensions, and transport type.
2. The system retrieves the appropriate pricing rule based on transport type and year.
3. Freight cost is calculated using base rate, weight, and volume.
4. The final result is rounded and returned.

If no pricing rule is found, an exception is thrown.

## Example

```scala id="sx3g8r"
val shipment = Shipment(10, Dimensions(2, 2, 2), TransportType.TRUCK)
val price = service.calculate(shipment, 2024)
```

## Running the Project

### Requirements

* Java 17+
* sbt

### Compile

```id="p3h3wq"
sbt compile
```

### Run Tests

```id="lrmc7k"
sbt test
```

## Testing

The project includes a comprehensive test suite covering:

* Pricing per transport type
* Year-based pricing changes
* Volume calculation correctness
* Rounding behavior
* Edge cases (zero values, large shipments)
* Missing rule handling
* Deterministic results across runs

All tests are designed to pass and validate the correctness of the system.

## Design Decisions

### Separation of Concerns

* Models represent domain data
* Repository handles rule retrieval
* Service handles business logic

### Deterministic Calculations

BigDecimal is used to ensure consistent financial rounding.

### Extensibility

New transport types or pricing strategies can be added without modifying existing logic.

### In-Memory Repository

Chosen for simplicity and performance. Can be replaced by a database-backed implementation.

## Possible Improvements

* External data source (database or API)
* Currency support
* Distance-based pricing
* Discounts and surcharges
* Real-time pricing updates
* REST API layer
