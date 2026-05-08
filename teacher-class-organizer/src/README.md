# Teacher Class Organizer / Optimizer (Scala 3)

## Overview

This project is a Teacher Class Organizer and Optimizer built with Scala 3.

The system manages:

* Teachers
* Subjects
* Student groups
* Classrooms
* Class scheduling
* Conflict detection
* Free slot optimization

It validates scheduling rules and guarantees consistent behavior through a deep automated test suite.

The architecture is designed to be simple, modular, deterministic, and easy to extend.

---

# Features

## Scheduling

* Schedule classes
* Remove classes
* List all sessions
* List teacher schedules
* List classroom schedules

## Validation

* Prevent teacher conflicts
* Prevent classroom conflicts
* Prevent student group conflicts
* Validate classroom capacity
* Validate supported teaching subjects
* Validate invalid time slots

## Optimization

* Calculate available free slots
* Organize sessions by time order

## Additional Features

* High-volume scheduling support
* Deterministic sorting
* Fully tested architecture

---

# Project Structure

```text id="ydvnlv"
teacher-class-organizer/
├── build.sbt
├── src/
│   ├── main/
│   │   └── scala/school/
│   │       ├── Subject.scala
│   │       ├── Classroom.scala
│   │       ├── Teacher.scala
│   │       ├── StudentGroup.scala
│   │       ├── ClassSession.scala
│   │       └── ClassOrganizer.scala
│   └── test/
│       └── scala/school/
│           └── ClassOrganizerSpec.scala
```

---

# Core Components

## Subject

Represents school subjects:

* Math
* Science
* History
* English
* Art

Implemented as a Scala enum for type safety.

---

## Classroom

Represents a physical classroom:

* Unique identifier
* Maximum capacity

---

## Teacher

Represents a teacher:

* Unique ID
* Name
* Supported subjects

The system validates subject compatibility before scheduling.

---

## StudentGroup

Represents a group of students:

* Unique ID
* Group size

---

## ClassSession

Represents a scheduled class:

* Teacher
* Student group
* Subject
* Classroom
* Time slot

---

## ClassOrganizer

The main scheduling engine.

Responsibilities:

* Schedule sessions
* Validate conflicts
* Remove sessions
* Query schedules
* Optimize free slots

---

# Scheduling Rules

## Teacher Conflict

A teacher cannot teach two classes at the same time.

---

## Classroom Conflict

A classroom cannot host multiple classes simultaneously.

---

## Student Group Conflict

A student group cannot attend multiple classes simultaneously.

---

## Subject Validation

Teachers can only teach subjects they support.

---

## Capacity Validation

Student group size cannot exceed classroom capacity.

---

# Example Usage

```scala id="2dlfm7"
val organizer = new ClassOrganizer

val teacher = Teacher(
  "t1",
  "John",
  Set(Subject.MATH)
)

val group = StudentGroup("g1", 20)

val room = Classroom("c1", 30)

organizer.schedule(
  teacher,
  group,
  Subject.MATH,
  room,
  1
)
```

---

# Running the Project

## Requirements

* Java 17+
* sbt

---

## Compile

```bash id="3z9h1m"
sbt compile
```

---

## Run Tests

```bash id="smbf0j"
sbt test
```

---

# Testing

The project contains deep automated tests covering:

* Successful scheduling
* Teacher conflicts
* Classroom conflicts
* Student group conflicts
* Invalid subjects
* Capacity validation
* Session removal
* Missing session removal
* Ordered schedules
* Free slot optimization
* High-volume scheduling
* Invalid slot validation
* Deterministic reads

The tests guarantee correctness and consistency across all operations.

---

# Design Decisions

## In-Memory Scheduling

All sessions are stored in memory:

* Fast execution
* Simpler architecture
* Easier testing

---

## Ordered Results

Schedules are always returned sorted by time slot:

* Predictable output
* Stable tests
* Easier analysis

---

## Explicit Conflict Validation

The organizer validates conflicts before insertion:

* Prevents invalid states
* Guarantees consistency

---

## Strong Typing

The project uses:

* Scala enums
* Case classes
* Immutable structures where possible

This improves readability and safety.

---

# Limitations

Current limitations include:

* No database persistence
* No recurring schedules
* No calendar dates
* No distributed scheduling
* No REST API
* No authentication
* No timetable export

---

# Possible Improvements

Potential future enhancements:

* Persistent database support
* Weekly schedules
* Multiple campuses
* REST API
* Graphical timetable generation
* Teacher workload balancing
* AI schedule optimization
* Room equipment matching
* Attendance tracking
* Real-time conflict resolution

---

# Why This Project Exists

This project demonstrates:

* Scheduling systems
* Constraint validation
* Conflict resolution
* Scala 3 architecture
* Clean object modeling
* Deterministic testing
* In-memory system design
