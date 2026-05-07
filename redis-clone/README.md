# Redis Clone Client/Server (Scala 3)

## Overview

This project is a lightweight Redis-inspired in-memory client/server system built with Scala 3.

It supports:

* String operations
* Map operations
* Concurrent-safe storage
* Client/server architecture
* High-volume operations
* Deterministic behavior through deep automated testing

The implementation focuses on simplicity, correctness, and extensibility while keeping the architecture clean and easy to understand.

## Features

### String Operations

* `set`
* `get`
* `remove`
* `append`

### Map Operations

* `mapSet`
* `mapGet`
* `mapKeys`
* `mapValues`

### Additional Features

* Thread-safe storage using concurrent collections
* In-memory key/value database
* Independent map namespaces
* Unicode support
* High-volume operation support
* Fully tested behavior

---

# Project Structure

```text id="bgb2po"
redis-clone/
├── build.sbt
├── src/
│   ├── main/
│   │   └── scala/redis/
│   │       ├── RedisValue.scala
│   │       ├── RedisServer.scala
│   │       └── RedisClient.scala
│   └── test/
│       └── scala/redis/
│           └── RedisServerSpec.scala
```

---

# Architecture

## RedisServer

The `RedisServer` class is the core in-memory database engine.

Responsibilities:

* Store string values
* Store map values
* Handle concurrent access
* Execute CRUD operations
* Maintain isolation between structures

It internally uses:

```scala id="cslzbm"
TrieMap
```

to provide thread-safe operations.

---

## RedisClient

The `RedisClient` acts as the client API layer.

Responsibilities:

* Communicate with the server
* Expose user-friendly methods
* Abstract direct server manipulation

The client delegates all operations to the server instance.

---

## RedisValue

Represents supported Redis-like value types:

* `StringValue`
* `MapValue`

This structure makes future expansion easier.

---

# Supported Commands

## String Commands

### SET

```scala id="x4czvx"
client.set("name", "john")
```

### GET

```scala id="kic7p6"
client.get("name")
```

### REMOVE

```scala id="3s0ph7"
client.remove("name")
```

### APPEND

```scala id="fuy7x8"
client.append("message", "-world")
```

---

## Map Commands

### MAP SET

```scala id="c6y0gz"
client.mapSet("users", "1", "john")
```

### MAP GET

```scala id="d5rlb0"
client.mapGet("users", "1")
```

### MAP KEYS

```scala id="9x6yxg"
client.mapKeys("users")
```

### MAP VALUES

```scala id="g4u25e"
client.mapValues("users")
```

---

# Example Usage

```scala id="9mq56l"
val server = new RedisServer
val client = new RedisClient(server)

client.set("name", "john")

println(client.get("name"))

client.mapSet("users", "1", "mary")

println(client.mapGet("users", "1"))
```

---

# Running the Project

## Requirements

* Java 17+
* sbt

---

## Compile

```bash id="clh4i0"
sbt compile
```

---

## Run Tests

```bash id="zqchxe"
sbt test
```

---

# Testing

The project includes deep automated tests covering:

* String insertion
* String retrieval
* String deletion
* String append behavior
* Missing key handling
* Map insertion
* Map retrieval
* Map overwrite behavior
* Sorted key retrieval
* Empty map scenarios
* Large-volume operations
* Multiple append operations
* Independent map isolation
* Unicode support
* Deterministic repeated reads

The tests validate both correctness and consistency.

---

# Design Decisions

## In-Memory Database

The system stores everything in memory:

* Fast access
* Simpler architecture
* Ideal for testing and learning

---

## Thread Safety

The implementation uses:

```scala id="jxk6s2"
TrieMap
```

to guarantee concurrent-safe operations.

---

## Sorted Keys

Map keys are returned sorted:

* Deterministic results
* Stable testing
* Predictable output

---

## Separate Stores

The server maintains:

* One store for strings
* One store for maps

This simplifies internal logic and future extensibility.

---

# Limitations

Current limitations include:

* No persistence
* No network sockets
* No expiration/TTL
* No transactions
* No pub/sub
* No authentication
* No clustering
* No replication

---

# Possible Improvements

Potential future enhancements:

* TCP server support
* Persistence to disk
* TTL expiration
* Pub/Sub system
* Transactions
* Snapshotting
* Replication
* REST API
* Binary serialization
* Command parser
* Real Redis protocol compatibility

---

# Why This Project Exists

This project demonstrates:

* Concurrent programming
* In-memory database design
* Client/server abstraction
* Scala 3 architecture
* Deterministic testing
* Clean code organization
