# Calendar System (Scala 3)

## Overview

This project implements a Calendar Management System in Scala 3 capable of booking meetings, removing meetings, listing scheduled meetings, and suggesting the best available time for two people.

The system validates scheduling conflicts, respects meeting boundaries, and provides deterministic behavior through a comprehensive automated test suite.

The project follows a clean architecture with separated model, repository, and service layers.

## Features

* Book meetings
* Remove meetings
* List meetings by user
* Detect overlapping meetings
* Suggest the best available meeting time for two people
* Validate meeting time ranges
* Handle high-volume scheduling scenarios
* Deterministic and fully tested behavior

## Project Structure

```text
calendar-system/
├── build.sbt
├── src/
│   ├── main/
│   │   └── scala/calendar/
│   │       ├── Meeting.scala
│   │       ├── MeetingRepository.scala
│   │       └── CalendarService.scala
│   └── test/
│       └── scala/calendar/
│           └── CalendarServiceSpec.scala
```

## Core Concepts

### Meeting

Represents a scheduled meeting:

* Unique ID
* Owner
* Title
* Start time
* End time

### MeetingRepository

Responsible for data storage:

* Save meetings
* Remove meetings
* Find meetings
* List meetings
* Filter meetings by owner

### CalendarService

Contains business rules:

* Meeting booking validation
* Conflict detection
* Meeting removal
* Ordered listing
* Best time suggestion algorithm

## How It Works

### Booking Meetings

When a user books a meeting:

1. The system validates the date range.
2. Existing meetings for the same user are checked.
3. If a conflict exists, an exception is thrown.
4. Otherwise, the meeting is saved.

### Conflict Detection

Two meetings overlap when:

* One meeting starts before the other ends
* And the other starts before the first ends

Edge-touching meetings are allowed:

* 10:00 → 11:00
* 11:00 → 12:00

### Best Time Suggestion

The system:

1. Searches between 08:00 and 18:00
2. Uses 30-minute intervals
3. Finds the first available slot for both users
4. Returns the best available start time

## Example

```scala
val repo = new MeetingRepository
val service = new CalendarService(repo)

service.bookMeeting(
  "john",
  "Daily Sync",
  LocalDateTime.of(2025, 1, 10, 9, 0),
  LocalDateTime.of(2025, 1, 10, 10, 0)
)

val meetings = service.listMeetings("john")
```

## Running the Project

### Requirements

* Java 17+
* sbt

### Compile

```bash
sbt compile
```

### Run Tests

```bash
sbt test
```

## Testing

The project includes deep test coverage for:

* Successful meeting booking
* Conflict detection
* Meeting removal
* Invalid date ranges
* Meeting ordering
* Best time suggestions
* Full-day busy scenarios
* Edge-touching meeting rules
* High-volume scheduling
* Deterministic repeated operations

All tests are deterministic and designed to guarantee correctness.

## Design Decisions

### In-Memory Repository

The repository uses in-memory storage:

* Fast execution
* Easy testing
* No external dependencies

### Sorted Meeting Listings

Meetings are always returned ordered by start time to simplify usage.

### Conflict Validation

Conflict validation is scoped per owner:

* Different users may overlap
* Same user cannot overlap meetings

### Time Suggestion Granularity

The suggestion algorithm uses:

* 30-minute intervals
* Working hours between 08:00 and 18:00

## Limitations

* No persistent database
* No recurring meetings
* No time zone handling
* No attendee groups
* No notifications

## Possible Improvements

* Database integration
* Recurring meetings
* Time zone support
* Multiple attendees
* Email notifications
* REST API
* Calendar sharing
* Advanced scheduling heuristics
