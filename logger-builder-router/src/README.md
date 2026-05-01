# Logger Builder Router System (Scala 3)

## Overview

This project implements a flexible and extensible logging system in Scala 3. It allows applications to log messages to multiple destinations using a unified API, with support for both synchronous and asynchronous execution modes.

The system is designed to be simple, composable, and thread-safe, while still demonstrating real-world concerns such as concurrency and routing.

## Features

* Unified logging API (`info`, `warn`, `error`)
* Multiple log targets (File System, In-Memory, Composite)
* Synchronous and Asynchronous logging modes
* Thread-safe in-memory logging
* Configurable routing to multiple destinations
* Deterministic and reliable asynchronous testing

## Project Structure

```
logger-builder-router/
├── build.sbt
├── src/
│   ├── main/
│   │   └── scala/logger/
│   │       ├── model.scala
│   │       ├── targets.scala
│   │       ├── dispatcher.scala
│   │       └── logger.scala
│   └── test/
│       └── scala/logger/
│           └── LoggerSpec.scala
```

## Core Concepts

### LogLevel

Represents the severity of a log message:

* INFO
* WARN
* ERROR

### LogMessage

Encapsulates:

* Log level
* Message content
* Timestamp

### LogTarget

Defines where logs are sent. Implementations include:

* FileSystemLogTarget: writes logs to a file
* InMemoryLogTarget: stores logs in a thread-safe queue
* CompositeLogTarget: routes logs to multiple targets

### Mode

Defines how logs are processed:

* SYNC: immediate execution
* ASYNC: executed using Futures

### LogDispatcher

Responsible for routing log messages to targets based on the selected mode.

### Logger

Provides the public API:

* info(...)
* warn(...)
* error(...)

### LoggerBuilder

Factory responsible for creating Logger instances with the desired configuration.

## How It Works

1. A Logger is built with a target and execution mode.
2. A log method is called (info, warn, error).
3. A LogMessage is created.
4. The dispatcher routes the message.
5. The target processes the message.

## Example Usage

```scala
import logger.*
import scala.concurrent.ExecutionContext.Implicits.global

val target = new FileSystemLogTarget("app.log")
val logger = LoggerBuilder.build(target, Mode.ASYNC)

logger.info("Application started")
logger.error("Something went wrong")
```

## Running the Project

### Requirements

* Java 17+
* sbt

### Compile

```
sbt compile
```

### Run Tests

```
sbt test
```

## Testing

The project includes comprehensive tests that validate:

* Synchronous logging behavior
* Asynchronous logging completion
* Multiple log levels
* Composite routing
* File system persistence
* High-volume logging scenarios
* Thread safety under concurrency

Asynchronous tests use a retry-based approach to ensure reliability without relying on fixed delays.

## Design Decisions

### Thread Safety

InMemoryLogTarget uses a concurrent queue to avoid race conditions in async scenarios.

### Separation of Concerns

* Logger handles API
* Dispatcher handles execution mode
* Targets handle output

### Composability

CompositeLogTarget allows flexible routing without modifying existing code.

### Asynchronous Reliability

Tests avoid timing assumptions by using eventual consistency checks.

## Possible Improvements

* Structured logging (JSON format for ELK integration)
* Batching and buffering strategies
* Backpressure handling
* Retry and failure policies
* Pluggable external systems (Kafka, Elasticsearch, etc.)
* Log filtering and formatting pipelines
