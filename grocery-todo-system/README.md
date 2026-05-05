# Grocery TODO List System (Scala 3)

## Overview

This project implements a Grocery TODO List system using Scala 3. It allows users to manage grocery items by adding, removing, marking as done, redoing tasks, and listing all items.

The system is designed to be simple, deterministic, and fully testable, with strong emphasis on correctness and clean separation of concerns.

## Features

* Add grocery items
* Remove items
* Mark items as done
* Redo (mark items back to TODO)
* Execute actions using a unified API (`doItem`)
* List all items
* Track total number of items
* Unique ID generation for each item
* Comprehensive test coverage

## Project Structure

```id="m7x2p1"
grocery-todo-system/
├── build.sbt
├── src/
│   ├── main/
│   │   └── scala/grocery/
│   │       ├── model.scala
│   │       ├── repository.scala
│   │       └── service.scala
│   └── test/
│       └── scala/grocery/
│           └── GrocerySpec.scala
```

## Core Concepts

### Status

Represents the state of an item:

* TODO
* DONE

### Item

Represents a grocery task with:

* Unique ID
* Name
* Status

### GroceryRepository

Handles data storage and retrieval:

* Add items
* Remove items
* Update items
* Find items
* List all items
* Track total size

### GroceryService

Provides business logic:

* Creates items with unique IDs
* Marks items as done or TODO
* Removes items
* Lists all items
* Coordinates repository operations

## How It Works

1. A new item is created with a unique ID and default status TODO.
2. Items can be marked as DONE using `markDone` or `doItem`.
3. Items can be reset back to TODO using `redo`.
4. Items can be removed from the system.
5. All items can be retrieved at any time.

## Example

```scala id="q9v4tz"
val repo = new GroceryRepository
val service = new GroceryService(repo)

val item = service.addItem("Milk")

service.markDone(item.id)

val allItems = service.listAll()
```

## Running the Project

### Requirements

* Java 17+
* sbt

### Compile

```id="c3k8pd"
sbt compile
```

### Run Tests

```id="n2w7ls"
sbt test
```

## Testing

The project includes a comprehensive test suite covering:

* Adding and removing items
* Status transitions (TODO ↔ DONE)
* Redo functionality
* Listing items
* Edge cases (invalid IDs)
* High-volume scenarios
* Consistency across multiple operations

All tests are isolated and deterministic, ensuring reliable results.

## Design Decisions

### In-Memory Storage

The repository uses an in-memory mutable map for simplicity and performance. It can be replaced with persistent storage if needed.

### Unique Identification

Each item is assigned a UUID to ensure uniqueness.

### Separation of Concerns

* Model defines data structures
* Repository manages storage
* Service handles business logic

### Deterministic Behavior

All operations produce predictable results, making the system easy to test and reason about.

## Limitations

* No persistence layer
* No concurrency control
* No prioritization or categorization of items

## Possible Improvements

* Persistent database integration
* Priority levels or categories
* Expiration dates
* Notifications or reminders
* REST API or UI layer
