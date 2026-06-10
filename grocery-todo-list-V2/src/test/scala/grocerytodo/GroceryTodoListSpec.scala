package grocerytodo

import org.scalatest.funsuite.AnyFunSuite

final class GroceryTodoListSpec
  extends AnyFunSuite {

  test(
    "should add grocery item"
  ) {

    val result =
      GroceryTodoList
        .empty
        .addItem(
          "bread"
        )

    assert(
      result.items.size == 1
    )
  }

  test(
    "should remove grocery item"
  ) {

    val result =
      GroceryTodoList(
        Vector(
          GroceryItem(
            "milk"
          ),
          GroceryItem(
            "apple"
          )
        )
      )
        .removeItem(
          "milk"
        )

    assert(
      result.items.size == 1
    )
  }

  test(
    "should mark item as done"
  ) {

    val result =
      GroceryTodoList(
        Vector(
          GroceryItem(
            "tea"
          )
        )
      )
        .markAsDone(
          "tea"
        )

    assert(
      result
        .items
        .head
        .done
    )
  }

  test(
    "should redo item"
  ) {

    val result =
      GroceryTodoList(
        Vector(
          GroceryItem(
            "juice",
            true
          )
        )
      )
        .redoItem(
          "juice"
        )

    assert(
      !result
        .items
        .head
        .done
    )
  }

  test(
    "should list all items"
  ) {

    val result =
      GroceryTodoList(
        Vector(
          GroceryItem(
            "eggs"
          ),
          GroceryItem(
            "flour"
          )
        )
      )

    assert(
      result
        .listAll
        .size == 2
    )
  }
}