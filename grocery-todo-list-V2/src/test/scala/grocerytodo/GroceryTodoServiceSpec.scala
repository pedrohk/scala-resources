package grocerytodo

import org.scalatest.funsuite.AnyFunSuite

final class GroceryTodoServiceSpec
  extends AnyFunSuite {

  test(
    "should add through service"
  ) {

    val result =
      GroceryTodoService
        .add(
          GroceryTodoList.empty,
          "rice"
        )

    assert(
      result.nonEmpty
    )
  }

  test(
    "should reject empty item"
  ) {

    val result =
      GroceryTodoService
        .add(
          GroceryTodoList.empty,
          ""
        )

    assert(
      result.isEmpty
    )
  }

  test(
    "should mark existing item"
  ) {

    val result =
      GroceryTodoService
        .markDone(
          GroceryTodoList(
            Vector(
              GroceryItem(
                "tomato"
              )
            )
          ),
          "tomato"
        )

    assert(
      result
        .get
        .items
        .head
        .done
    )
  }

  test(
    "should not mark missing item"
  ) {

    val result =
      GroceryTodoService
        .markDone(
          GroceryTodoList.empty,
          "missing"
        )

    assert(
      result.isEmpty
    )
  }

  test(
    "should execute workflow using for comprehension"
  ) {

    val result =
      GroceryTodoService
        .workflow()

    assert(
      result.nonEmpty
    )

    assert(
      result
        .get
        .items
        .size == 2
    )

    assert(
      result
        .get
        .items
        .forall(
          !_.done
        )
    )
  }

  test(
    "should remove item in workflow"
  ) {

    val result =
      GroceryTodoService
        .workflow()

    assert(
      !result
        .get
        .items
        .exists(
          _.name == "pear"
        )
    )
  }
}