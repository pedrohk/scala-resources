# Custom String Implementation

A complete Scala 3.8.3 project that implements a custom immutable string structure from scratch without relying on the built-in `String` methods for the core behaviors.

The project recreates common string operations manually using arrays, loops, iterators, and character processing while keeping the implementation simple, deterministic, and fully testable.

---

# Features

The project implements the following operations:

* `toArray`
* `foreach`
* `reverse`
* `iterator`
* `length`
* `charAt`
* `equals`
* `isEmpty`
* `replace`
* `substring`
* `trim`
* `toJson`
* `indexOf`
* `hashCode`

---

# Technologies

* Scala 3.8.3
* SBT
* ScalaTest 3.2.18

---

# Project Structure

```text
custom-string-implementation/
├── build.sbt
├── src
│   ├── main
│   │   └── scala
│   │       └── stringimpl
│   │           └── MyString.scala
│   └── test
│       └── scala
│           └── stringimpl
│               └── MyStringSpec.scala
```

---

# Core Design

The implementation stores characters internally as:

```scala
Array[Char]
```

This allows the project to manually implement low-level string operations instead of delegating behavior to the standard library.

The class is immutable because every transformation returns a new `MyString` instance.

---

# Example Usage

```scala
import stringimpl.MyString

val text = new MyString("scala")

println(text.lengthValue)
println(text.reverseString)
println(text.charAt(2))
println(text.replace('a', 'o'))
println(text.substring(1, 4))
println(text.trimValue)
println(text.indexOf('l'))
println(text.toJson)
```

---

# Implemented Operations

## Reverse

Reverses the string manually using indexed iteration.

```scala
val text = new MyString("scala")

println(text.reverseString)
```

Output:

```text
alacs
```

---

## Replace

Replaces matching characters manually.

```scala
val text = new MyString("banana")

println(text.replace('a', 'o'))
```

Output:

```text
bonono
```

---

## Substring

Creates a new custom string using explicit index copying.

```scala
val text = new MyString("abcdef")

println(text.substring(1, 4))
```

Output:

```text
bcd
```

---

## Trim

Removes leading and trailing whitespaces.

```scala
val text = new MyString("   scala   ")

println(text.trimValue)
```

Output:

```text
scala
```

---

## JSON Conversion

Escapes special JSON characters.

```scala
val text = new MyString("hello \"scala\"")

println(text.toJson)
```

Output:

```text
"hello \"scala\""
```

---

# Testing

The project contains deep automated tests covering:

* normal cases
* edge cases
* invalid indexes
* empty strings
* iterator behavior
* equality consistency
* deterministic hashCode behavior
* transformation chaining
* JSON escaping
* substring boundaries
* whitespace trimming

---

# Running Tests

Run all tests with:

```bash
sbt test
```

---

# Design Decisions

## Why Use `Array[Char]`

Using `Array[Char]` provides:

* predictable memory layout
* direct indexed access
* low-level manipulation
* manual implementation of algorithms

---

## Why Immutable Objects

Every operation returns a new instance instead of mutating the current object.

Advantages:

* thread safety
* deterministic behavior
* easier debugging
* safer chaining

Tradeoff:

* additional allocations during transformations

---

# Example Test Coverage

The test suite validates scenarios such as:

```scala
assert(value.reverseString.toString == "alacs")

assert(value.indexOf('z') == -1)

assertThrows[IndexOutOfBoundsException] {
  value.charAt(100)
}
```

---

# Goals of the Project

This project was created to demonstrate:

* manual string algorithm implementation
* custom collection-like structures
* immutable object design
* iterator implementation
* low-level character manipulation
* defensive programming
* Scala 3 object-oriented development
* deep unit testing strategies

---

# Future Improvements

Possible future extensions:

* UTF-8 support
* mutable string builder
* regex operations
* split/join methods
* streaming parser support
* serialization support
* custom encoding strategies
* Boyer-Moore search optimization
* rope-based large string implementation
