package P12

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class P03Test extends AnyFunSuite with Matchers {

  private val solver = new P03()

  test("decodes the example from the problem statement") {
    val input = List(
      (4, Symbol("a")),
      (1, Symbol("b")),
      (2, Symbol("c")),
      (2, Symbol("a")),
      (1, Symbol("d")),
      (4, Symbol("e"))
    )
    val expected = List(
      Symbol("a"), Symbol("a"), Symbol("a"), Symbol("a"),
      Symbol("b"),
      Symbol("c"), Symbol("c"),
      Symbol("a"), Symbol("a"),
      Symbol("d"),
      Symbol("e"), Symbol("e"), Symbol("e"), Symbol("e")
    )
    solver.decode(input) shouldBe expected
  }

  test("returns an empty list when given an empty list") {
    solver.decode(List.empty[(Int, Symbol)]) shouldBe Nil
  }

  test("decodes a single tuple with count one") {
    solver.decode(List((1, Symbol("x")))) shouldBe List(Symbol("x"))
  }

  test("decodes a single tuple with a larger count") {
    solver.decode(List((5, Symbol("z")))) shouldBe
      List(Symbol("z"), Symbol("z"), Symbol("z"), Symbol("z"), Symbol("z"))
  }

  test("decodes several tuples all with count one") {
    solver.decode(List((1, Symbol("a")), (1, Symbol("b")), (1, Symbol("c")))) shouldBe
      List(Symbol("a"), Symbol("b"), Symbol("c"))
  }

  test("works with integers as the element type") {
    solver.decode(List((3, 1), (2, 2), (1, 3))) shouldBe List(1, 1, 1, 2, 2, 3)
  }

  test("works with strings as the element type") {
    solver.decode(List((2, "a"), (1, "b"), (3, "c"))) shouldBe
      List("a", "a", "b", "c", "c", "c")
  }

  test("handles a count large enough to exercise multi-step expansion") {
    solver.decode(List((10, Symbol("w")))) shouldBe List(
      Symbol("w"), Symbol("w"), Symbol("w"), Symbol("w"), Symbol("w"),
      Symbol("w"), Symbol("w"), Symbol("w"), Symbol("w"), Symbol("w")
    )
  }
}
