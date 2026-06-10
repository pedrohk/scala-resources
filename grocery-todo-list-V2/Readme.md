# Grocery TODO List V2

A functional Grocery TODO List application built with **Scala 3.8.4** that demonstrates immutable state management, `for` comprehensions, and complete automated testing using **ScalaTest 3.2.20**.

The project allows managing grocery items through a simple TODO workflow:

* Add items
* Remove items
* Mark items as completed
* Re-open completed items
* List all items
* Execute chained operations using `for` comprehension

## Technologies

* Scala 3.8.4
* ScalaTest 3.2.20
* SBT

---

## Project Structure

```text
grocery-todo-list
├── build.sbt
└── src
    ├── main
    │   └── scala
    │       └── grocerytodo
    │           ├── GroceryItem.scala
    │           ├── GroceryTodoList.scala
    │           ├── GroceryTodoService.scala
    │           └── Main.scala
    └── test
        └── scala
            └── grocerytodo
                ├── GroceryItemSpec.scala
                ├── GroceryTodoListSpec.scala
                └── GroceryTodoServiceSpec.scala
```

---

## Features

### Add Grocery Items

Insert new grocery tasks into the list.

Example:

```text
coffee
olive oil
pear
```

---

### Remove Grocery Items

Delete items from the list while preserving immutability.

---

### Mark Items as Done

Mark a grocery task as completed.

Example:

```text
olive oil → completed
```

---

### Re-open Completed Items

Undo a completed state and return the item to active status.

---

### List All Items

Retrieve all items currently stored.

---

### Functional Workflow with For Comprehension

The service layer demonstrates operation composition using Scala `for` comprehension.

Example flow:

```scala
for {
    initial <- Option(GroceryTodoList.empty)
    first <- add(initial, "coffee")
    second <- add(first, "olive oil")
    third <- add(second, "pear")
    fourth <- markDone(third, "olive oil")
    fifth <- redo(fourth, "olive oil")
    finalList <- remove(fifth, "pear")
} yield finalList
```

---

## Running the Application

Start the application:

```bash
sbt run
```

Example output:

```text
GroceryItem(coffee,false)
GroceryItem(olive oil,false)
```

---

## Running Tests

Execute the complete test suite:

```bash
sbt test
```

Run a single test class:

```bash
sbt "testOnly grocerytodo.GroceryTodoServiceSpec"
```

---

## Testing Coverage

The test suite validates:

* Item creation
* Add operations
* Remove operations
* Completion flow
* Re-open flow
* Listing behavior
* Service validations
* Workflow execution
* For comprehension chaining

All tests are designed to pass with:

```text
Scala 3.8.4
ScalaTest 3.2.20
```

---
