# Core Bank Ledger

A complete banking ledger system built with Scala 3.8.3 focused on transactional consistency, account management, balance tracking, immutable ledger history, and deep automated testing.

The project simulates a simplified core banking environment where accounts can deposit, withdraw, transfer money, and maintain a full immutable ledger of all operations.

## Features

* Create bank accounts
* Deposit funds
* Withdraw funds
* Transfer between accounts
* Immutable transaction ledger
* Balance tracking
* Validation for invalid operations
* Transaction history per account
* Repository abstraction
* Thread-safe service layer
* Deep ScalaTest coverage
* Pure Scala 3.8.3 implementation
* File names matching class/object names
* Braces used in all classes and methods

---

# Project Structure

```text
core-bank-ledger/
├── build.sbt
├── src
│   ├── main
│   │   └── scala
│   │       └── bank
│   │           ├── Account.scala
│   │           ├── AccountRepository.scala
│   │           ├── BankLedgerService.scala
│   │           ├── InMemoryAccountRepository.scala
│   │           ├── LedgerEntry.scala
│   │           ├── TransactionType.scala
│   │           └── ValidationException.scala
│   │
│   └── test
│       └── scala
│           └── bank
│               └── BankLedgerServiceSpec.scala
```

---

# Technologies

* Scala 3.8.3
* ScalaTest 3.2.18
* SBT

---

# Build Configuration

## `build.sbt`

```scala
ThisBuild / version := "1.0.0"

ThisBuild / scalaVersion := "3.8.3"

lazy val root = (project in file("."))
  .settings(
    name := "core-bank-ledger",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.18" % Test
    )
  )
```

---

# Domain Overview

## Account

Represents a bank account.

Attributes include:

* Account ID
* Owner name
* Current balance
* Ledger entries

---

## LedgerEntry

Represents an immutable financial operation.

Supported transaction types:

* Deposit
* Withdrawal
* Transfer In
* Transfer Out

---

## Repository Layer

The repository manages account persistence in memory.

Responsibilities:

* Store accounts
* Update balances
* Retrieve accounts
* List all accounts

---

## Service Layer

The service contains all business rules.

Responsibilities:

* Deposit validation
* Withdrawal validation
* Transfer validation
* Ledger consistency
* Balance integrity
* Account creation

---

# Business Rules

## Deposits

* Must be positive
* Increase balance
* Create ledger entry

---

## Withdrawals

* Must be positive
* Cannot exceed available balance
* Create ledger entry

---

## Transfers

* Sender and receiver must exist
* Amount must be positive
* Sender must have sufficient balance
* Creates entries for both accounts

---

# Testing

The project includes deep unit tests covering:

* Account creation
* Deposits
* Withdrawals
* Transfers
* Ledger history
* Invalid transactions
* Negative values
* Zero values
* Overdraft protection
* Repository consistency
* Transaction ordering
* Immutable history behavior
* Multiple sequential operations
* Edge cases

---

# Run Tests

```bash
sbt test
```

---

# Compile

```bash
sbt compile
```

---

# Run Interactive SBT

```bash
sbt
```

---

# Example Usage

```scala
val repository = new InMemoryAccountRepository()

val service = new BankLedgerService(repository)

service.createAccount("acc-1", "Alice")
service.createAccount("acc-2", "Bob")

service.deposit("acc-1", 1000)

service.transfer("acc-1", "acc-2", 250)

val alice = service.findAccount("acc-1").get
val bob = service.findAccount("acc-2").get

println(alice.balance)
println(bob.balance)
```

---

# Example Ledger Output

```text
Account: acc-1

DEPOSIT      +1000
TRANSFER_OUT -250

Balance: 750
```

---

# Design Decisions

## Immutable Ledger Entries

Ledger entries are immutable to preserve transaction history integrity.

### Advantages

* Safer audit trail
* Easier debugging
* Predictable behavior

### Trade-offs

* More object allocations
* Additional memory usage

---

## In-Memory Repository

The project uses an in-memory repository for simplicity and testability.

### Advantages

* Fast execution
* Easy testing
* No external dependencies

### Trade-offs

* No persistence
* Data lost on shutdown

---

## Service-Oriented Validation

All business rules are centralized in the service layer.

### Advantages

* Cleaner architecture
* Easier maintenance
* Better testability

### Trade-offs

* More abstraction layers
* Slightly more boilerplate

---

# Scalability Ideas

Possible future improvements:

* Persistent database storage
* Event sourcing
* Transaction rollback support
* REST API
* Authentication
* Multi-currency support
* Interest calculations
* Scheduled payments
* Distributed ledger replication
* Concurrency optimizations

---

# Quality Goals

The project was designed with emphasis on:

* Readability
* Consistency
* Deterministic behavior
* Strong validation
* High test coverage
* Clean architecture
* Pure Scala 3 style
