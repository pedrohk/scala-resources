# Ticket System (Scala 3)

## Overview

This project implements a ticketing system capable of managing seat reservations for different shows. It supports booking multiple tickets, selecting specific seats, handling different venue zones, and enforcing maximum capacity constraints.

The system is designed to be deterministic, simple, and reliable, with strong test coverage ensuring correctness.

## Features

* Book tickets for different shows
* Select specific seat numbers
* Support for multiple zones (VIP, Regular, Economy)
* Enforce maximum venue capacity
* Prevent double booking of seats
* Track reserved seats per show
* Calculate remaining capacity
* Fully tested with deterministic behavior

## Project Structure

```id="9r5xk2"
ticket-system/
├── build.sbt
├── src/
│   ├── main/
│   │   └── scala/ticket/
│   │       ├── model.scala
│   │       ├── repository.scala
│   │       └── service.scala
│   └── test/
│       └── scala/ticket/
│           └── TicketSystemSpec.scala
```

## Core Concepts

### Zone

Defines seating areas in the venue:

* VIP
* REGULAR
* ECONOMY

### Seat

Represents a seat with:

* Seat number
* Zone

### Show

Represents an event with:

* Unique ID
* Name
* Date
* Maximum capacity

### Ticket

Represents a successful reservation linking:

* Show ID
* Seat

### TicketRepository

Handles storage of reserved seats:

* Tracks reserved seats per show
* Prevents duplicate reservations
* Enforces capacity limits

### TicketService

Main entry point for business logic:

* Books tickets
* Checks seat availability
* Calculates remaining capacity

## How It Works

1. A show is defined with a maximum capacity.
2. Users request one or more seats.
3. The system checks:

   * If the seat is already reserved
   * If capacity has been reached
4. Valid reservations are stored.
5. Tickets are returned only for successful bookings.

## Example

```scala id="7kz4r1"
val repo = new TicketRepository
val service = new TicketService(repo)

val show = Show("1", "Concert", java.time.LocalDate.now(), 100)

val seats = List(
  Seat(1, Zone.VIP),
  Seat(2, Zone.REGULAR)
)

val tickets = service.buyTickets(show, seats)
```

## Running the Project

### Requirements

* Java 17+
* sbt

### Compile

```id="2xq9pm"
sbt compile
```

### Run Tests

```id="g8y3lv"
sbt test
```

## Testing

The project includes comprehensive tests covering:

* Single and multiple ticket bookings
* Prevention of double booking
* Capacity enforcement
* Seat availability checks
* High-volume scenarios
* Data consistency across operations
* Edge cases and failure conditions

Each test is isolated to ensure deterministic and reliable results.

## Design Decisions

### In-Memory Storage

A mutable in-memory structure is used for simplicity and performance. It can be replaced by persistent storage if needed.

### Seat-Based Reservation

Seats are uniquely identified by their number within a show, ensuring no duplicates.

### Capacity Enforcement

The system strictly enforces the maximum capacity defined per show.

### Test Isolation

Each test uses a fresh repository instance to avoid shared state and ensure correctness.

## Limitations

* No persistence layer
* No concurrency control
* No seat layout validation per zone
* No pricing system

## Possible Improvements

* Database integration
* Concurrent booking handling
* Seat map validation per zone
* Dynamic pricing by zone and demand
* Reservation expiration
* REST API or UI layer
