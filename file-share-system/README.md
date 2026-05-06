# FileShare System (Scala 3)

## Overview

This project implements a FileShare system in Scala 3 that allows storing, retrieving, deleting, listing, and searching files. All file contents are encrypted before storage and decrypted on retrieval.

The system is designed to be simple, deterministic, and fully covered by tests, ensuring correctness and reliability.

## Features

* Save files with encryption
* Restore (decrypt) file contents
* Soft delete files
* List active files
* Search files by name
* Case-insensitive search
* In-memory storage
* High test coverage
* Deterministic behavior

## Project Structure

```text
file-share-system/
├── build.sbt
├── src/
│   ├── main/
│   │   └── scala/fileshare/
│   │       ├── model.scala
│   │       ├── crypto.scala
│   │       ├── repository.scala
│   │       └── service.scala
│   └── test/
│       └── scala/fileshare/
│           └── FileServiceSpec.scala
```

## Core Concepts

### FileData

Represents a stored file:

* `id`: Unique identifier
* `name`: File name
* `encryptedContent`: Encrypted bytes
* `deleted`: Soft delete flag

### Crypto

Handles encryption and decryption:

* Uses a simple XOR-based algorithm
* Symmetric encryption (same operation for encrypt/decrypt)
* Ensures stored data is not in plain text

### FileRepository

In-memory storage layer:

* Save files
* Update files
* Retrieve files
* List all stored files

### FileService

Business logic layer:

* Encrypts content before saving
* Decrypts content when restoring
* Applies soft delete logic
* Filters deleted files from results
* Provides search functionality

## How It Works

1. A file is saved with a generated UUID.
2. Content is encrypted before being stored.
3. When restoring, content is decrypted.
4. Deleting a file marks it as deleted (soft delete).
5. Listing and searching ignore deleted files.

## Example

```scala
val repo = new FileRepository
val service = new FileService(repo)

val file = service.saveFile("notes.txt", "hello world")

val content = service.restoreFile(file.id)

val files = service.listFiles()

val results = service.search("note")
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

The project includes a comprehensive test suite covering:

* File saving and restoring
* Encryption correctness
* Deletion behavior
* Listing logic
* Search functionality
* Case-insensitive matching
* Edge cases (invalid IDs)
* High-volume scenarios
* Consistency across multiple operations

All tests are deterministic and designed to ensure correctness of the system.

## Design Decisions

### Encryption Strategy

A simple XOR-based encryption is used:

* Fast and deterministic
* Suitable for demonstration purposes
* Easily replaceable with stronger encryption if needed

### Soft Delete

Files are not physically removed:

* Enables safer operations
* Prevents accidental data loss
* Simplifies state management

### In-Memory Storage

* Fast and simple
* No external dependencies
* Easy to test

### Separation of Concerns

* Model defines data
* Crypto handles encryption
* Repository manages storage
* Service contains business logic

## Limitations

* Encryption is not production-grade
* No persistent storage
* No access control or authentication
* No file versioning

## Possible Improvements

* Replace XOR with AES encryption
* Add persistent storage (database or filesystem)
* Implement file versioning
* Add user authentication and permissions
* Expose as REST API
* Add streaming support for large files
