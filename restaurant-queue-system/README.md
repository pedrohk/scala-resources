# Restaurant Queue System (Scala 3)

## Overview

This project implements a restaurant queue system capable of estimating preparation and waiting times for orders. It models how a kitchen processes incoming orders and calculates how long each dish and order will take based on the current queue.

The system focuses on simplicity, determinism, and correctness, with a strong emphasis on test coverage.

## Features

* Estimate preparation time for individual dishes
* Calculate total time for an order with multiple items
* Track and manage a queue of orders
* Estimate total queue processing time
* Predict waiting time for new incoming orders
* Deterministic and consistent calculations

## Project Structure

```id="6e8r3n"
restaurant-queue-system/
├── build.sbt
├── src/
│   ├── main/
│   │   └── scala/restaurant/
│   │       ├── model.scala
│   │       ├── queue.scala
│   │       └── service.scala
│   └── test/
│       └── scala/restaurant/
│           └── RestaurantQueueSpec.scala
```

## Core Concepts

### Dish

Represents a menu item with a fixed preparation time.

### OrderItem

Represents a dish and its quantity within an order.

### Order

A collection of order items requested by a customer.

### KitchenQueue

A FIFO queue that stores incoming orders and represents the kitchen workload.

### TimeEstimator

Responsible for calculating:

* Time to prepare a single order
* Total time for all orders in the queue
* Expected waiting time for a new order

## How It Works

1. Each dish has a fixed preparation time.
2. An order aggregates multiple dishes and quantities.
3. The system calculates order time by summing all dish preparation times.
4. The queue processes orders sequentially.
5. Waiting time is calculated as the total queue time plus the new order time.

## Example

```scala id="l2d7q1"
val queue = new KitchenQueue
val estimator = new TimeEstimator

val pizza = Dish("Pizza", 10)
val order = Order(List(OrderItem(pizza, 2)))

queue.enqueue(order)

val waitTime = estimator.estimateWaitTime(queue, order)
```

## Running the Project

### Requirements

* Java 17+
* sbt

### Compile

```id="f9k2wx"
sbt compile
```

### Run Tests

```id="q3b7ye"
sbt test
```

## Testing

The project includes a comprehensive test suite covering:

* Single and multiple dish calculations
* Queue processing behavior
* Waiting time estimation
* Edge cases such as empty orders
* High-volume scenarios
* Consistency across repeated calculations

All tests are designed to pass and validate the correctness of the system.

## Design Decisions

### FIFO Queue

Orders are processed in the order they arrive, reflecting real kitchen behavior.

### Deterministic Timing

Preparation times are fixed per dish to ensure predictable results.

### Separation of Concerns

* Models define domain entities
* Queue manages order flow
* Service handles time calculations

### Simplicity

The system avoids unnecessary complexity while remaining extensible.

## Possible Improvements

* Parallel kitchen stations
* Priority orders
* Real-time updates
* Dynamic preparation times
* Integration with external ordering systems
* UI or API layer
