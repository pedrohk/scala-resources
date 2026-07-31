package P10

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class P07Test extends AnyFunSuite with Matchers {

  private val solver = new P07()

  test("encodes the example from the problem statement") {
    val input = List(
      Symbol("a"), Symbol("a"), Symbol("a"), Symbol("a"),
      Symbol("b"),
      Symbol("c"), Symbol("c"),
      Symbol("a"), Symbol("a"),
      Symbol("d"),
      Symbol("e"), Symbol("e"), Symbol("e"), Symbol("e")
    )
    val expected = List(
      (4, Symbol("a")),
      (1, Symbol("b")),
      (2, Symbol("c")),
      (2, Symbol("a")),
      (1, Symbol("d")),
      (4, Symbol("e"))
    )
    solver.encode(input) shouldBe expected
  }

  test("returns an empty list when given an empty list") {
    solver.encode(List.empty[Symbol]) shouldBe Nil
  }

  test("encodes a single-element list as a single tuple with count one") {
    solver.encode(List(Symbol("x"))) shouldBe List((1, Symbol("x")))
  }

  test("produces one tuple with count one per element when there are no duplicates") {
    solver.encode(List(Symbol("a"), Symbol("b"), Symbol("c"))) shouldBe
      List((1, Symbol("a")), (1, Symbol("b")), (1, Symbol("c")))
  }

  test("produces a single tuple when the whole list is one repeated element") {
    solver.encode(List(Symbol("x"), Symbol("x"), Symbol("x"))) shouldBe
      List((3, Symbol("x")))
  }

  test("keeps non-adjacent equal runs as separate tuples") {
    solver.encode(List(1, 1, 2, 2, 1)) shouldBe List((2, 1), (2, 2), (1, 1))
  }

  test("works with strings as the element type") {
    solver.encode(List("a", "a", "b", "c", "c", "c")) shouldBe
      List((2, "a"), (1, "b"), (3, "c"))
  }

  test("handles a run of exactly two consecutive duplicates followed by a single element") {
    solver.encode(List(1, 1, 2)) shouldBe List((2, 1), (1, 2))
  }
}
