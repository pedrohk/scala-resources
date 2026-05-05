# Guitar Factory System (Scala 3)

## Overview

This project implements a Guitar Factory System capable of building custom guitars based on specified configurations and managing them through an inventory system. It models a simplified manufacturing and storage workflow, focusing on flexibility, correctness, and testability.

The system allows you to define guitar models, customize specifications, generate unique instruments, and keep track of all created guitars.

## Features

* Build custom guitars with configurable specifications
* Support for multiple guitar types (Electric, Acoustic, Bass)
* Unique ID generation for each guitar
* Inventory management (add, remove, search, list)
* Search guitars by model
* Deterministic and consistent behavior
* Comprehensive test coverage

## Project Structure

```id="w9zq2m"
guitar-factory-system/
├── build.sbt
├── src/
│   ├── main/
│   │   └── scala/guitar/
│   │       ├── model.scala
│   │       ├── factory.scala
│   │       ├── inventory.scala
│   │       └── service.scala
│   └── test/
│       └── scala/guitar/
│           └── GuitarFactorySpec.scala
```

## Core Concepts

### GuitarType

Defines the category of the guitar:

* ELECTRIC
* ACOUSTIC
* BASS

### Specs

Represents the technical configuration of a guitar:

* Wood type
* Number of pickups
* Tremolo presence

### Model

Defines the guitar model and its type.

### Guitar

Represents a fully built instrument with:

* Unique ID
* Model
* Specifications
* OS (custom configuration/version tag)

### GuitarFactory

Responsible for creating guitars with unique identifiers.

### Inventory

Manages all guitars:

* Add guitars
* Remove guitars
* Find by ID
* Search by model
* Retrieve all items
* Track inventory size

### GuitarService

Acts as the main entry point:

* Creates and stores guitars
* Provides search and retrieval operations
* Coordinates factory and inventory

## How It Works

1. A model and specifications are defined.
2. The factory creates a new guitar with a unique ID.
3. The service stores the guitar in the inventory.
4. The inventory allows querying and managing stored guitars.

## Example

```scala id="z1t4k8"
val factory = new GuitarFactory
val inventory = new Inventory
val service = new GuitarService(factory, inventory)

val model = Model("Stratocaster", GuitarType.ELECTRIC)
val specs = Specs("Alder", 3, true)

val guitar = service.createAndStore(model, specs, "v1")
```

## Running the Project

### Requirements

* Java 17+
* sbt

### Compile

```id="l6d9rm"
sbt compile
```

### Run Tests

```id="y0f3pn"
sbt test
```

## Testing

The project includes comprehensive tests covering:

* Guitar creation and storage
* Unique ID generation
* Inventory operations (add, remove, search)
* Model-based queries
* Data consistency
* Edge cases (invalid removal, large volume)
* Specification integrity

All tests are designed to pass and ensure the correctness of the system.

## Design Decisions

### Unique Identification

Each guitar is assigned a UUID to guarantee uniqueness.

### Separation of Concerns

* Factory handles creation
* Inventory handles storage
* Service coordinates operations

### In-Memory Storage

Inventory uses an in-memory structure for simplicity and speed. It can be replaced with persistent storage if needed.

### Extensibility

The system is designed to easily support new guitar types, specifications, or storage mechanisms.

## Possible Improvements

* Persistent database integration
* Advanced search filters
* Pricing and sales module
* Order management system
* REST API layer
* UI for customization
