# Social Media Sharing Photo App

## Overview

The Social Media Sharing Photo App is a Scala 3 application that simulates a lightweight social media platform focused on photo sharing.

Users can:

* Publish photos
* Add tags to photos
* Comment on photos
* Browse a timeline
* Delete photos
* Retrieve photo details

The project was designed with clean architecture principles, immutable domain models, deterministic behavior, and a deep automated test suite.

---

# Features

## Photo Publishing

* Publish photos with descriptions
* Associate photos with users
* Prevent duplicate photo IDs
* Validate invalid input

---

## Tagging System

* Add tags to photos
* Support multiple tags
* Prevent duplicated tags automatically through sets

Examples:

* travel
* summer
* food
* nature

---

## Comments

* Add comments to photos
* Preserve comment order
* Store comment timestamps
* Associate comments with users

---

## Timeline

* Retrieve all published photos
* Sort timeline by newest first
* Deterministic ordering

---

## Photo Management

* Retrieve photos by ID
* Delete photos
* Validate missing resources

---

# Project Structure

```text
social-photo-app/
├── build.sbt
├── src/
│   ├── main/
│   │   └── scala/social/
│   │       ├── User.scala
│   │       ├── Tag.scala
│   │       ├── Comment.scala
│   │       ├── Photo.scala
│   │       ├── PhotoRepository.scala
│   │       └── TimelineService.scala
│   └── test/
│       └── scala/social/
│           └── TimelineServiceSpec.scala
```

---

# Architecture

## User

Represents a platform user.

Fields:

* id
* username

---

## Tag

Represents a photo tag.

Examples:

* travel
* food
* family

---

## Comment

Represents comments attached to photos.

Fields:

* id
* user
* message
* timestamp

---

## Photo

Represents a published photo.

Fields:

* id
* owner
* url
* description
* tags
* comments
* createdAt

The entity is immutable and returns updated copies when modified.

---

## PhotoRepository

In-memory repository responsible for:

* Saving photos
* Searching photos
* Deleting photos
* Listing photos
* Counting photos

Uses insertion-preserving storage internally.

---

## TimelineService

Main application service.

Responsibilities:

* Publish photos
* Tag photos
* Comment photos
* Delete photos
* Build timeline
* Validate business rules

---

# Business Rules

## Publishing Rules

* Photo ID cannot be empty
* URL cannot be empty
* Duplicate IDs are forbidden

---

## Comment Rules

* Comments cannot be empty
* Cannot comment missing photos

---

## Tag Rules

* Cannot tag missing photos

---

## Timeline Rules

* Timeline always returns newest photos first

---

# Example Usage

## Publish a Photo

```scala
val service = new TimelineService(new PhotoRepository())

val user = User("u1", "alice")

service.publishPhoto(
  "p1",
  user,
  "http://image",
  "summer vacation"
)
```

---

## Add a Tag

```scala
service.tagPhoto(
  "p1",
  Tag("travel")
)
```

---

## Add a Comment

```scala
service.commentPhoto(
  "p1",
  "c1",
  user,
  "Amazing photo"
)
```

---

## Retrieve Timeline

```scala
val timeline = service.timeline()
```

---

# Running the Project

## Requirements

* Java 17+
* sbt

---

## Compile

```bash
sbt compile
```

---

## Run Tests

```bash
sbt test
```

---

# Testing

The project includes deep automated tests covering:

* Photo publishing
* Duplicate photo validation
* Tagging photos
* Multiple tags
* Adding comments
* Comment ordering
* Timeline ordering
* Photo deletion
* Missing resource handling
* Invalid input validation
* Repository counting
* High-volume publishing
* Photo retrieval

The tests guarantee correctness and deterministic behavior.

---

# Design Decisions

## Immutable Domain Models

Core entities use immutable case classes.

Benefits:

* Safer state management
* Easier testing
* Predictable behavior

---

## In-Memory Repository

The repository stores data in memory.

Benefits:

* Fast execution
* Simpler architecture
* Easier testing

Limitations:

* No persistence
* Data lost after shutdown

---

## Deterministic Timeline

Timeline results are sorted by creation timestamp.

Benefits:

* Stable output
* Predictable user experience
* Reliable tests

---

# Scalability Considerations

Possible future improvements:

* Persistent database support
* Distributed storage
* Image uploads
* Authentication
* Followers/following
* Likes and reactions
* Search engine
* Pagination
* Notification system
* Real-time feeds
* Cloud storage integration
* REST API
* GraphQL API
* Rate limiting

---

# Technical Highlights

* Scala 3.8.3
* Strong typing
* Immutable models
* Layered architecture
* Repository pattern
* Service layer separation
* Deterministic sorting
* Extensive test coverage

---

# Why This Project Exists

This project demonstrates:

* Domain modeling in Scala
* Immutable entity design
* Service-oriented architecture
* Timeline/feed systems
* Repository abstraction
* Business rule validation
* Automated testing practices
