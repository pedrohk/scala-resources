package grocerytodo

import org.scalatest.funsuite.AnyFunSuite

final class GroceryItemSpec
  extends AnyFunSuite {

  test(
    "should create item as not done"
  ) {

    val item =
      GroceryItem(
        "rice"
      )

    assert(
      !item.done
    )
  }

  test(
    "should mark item as done"
  ) {

    val item =
      GroceryItem(
        "beans"
      )

    assert(
      item
        .markDone
        .done
    )
  }

  test(
    "should redo item"
  ) {

    val item =
      GroceryItem(
        "banana",
        true
      )

    assert(
      !item
        .redo
        .done
    )
  }
}