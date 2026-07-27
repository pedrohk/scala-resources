package P09

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class P09Spec extends AnyFunSuite with Matchers {

  private val solver = new P09()

  test("packs the example from the problem statement") {
    val input = List(
      Symbol("a"), Symbol("a"), Symbol("a"), Symbol("a"),
      Symbol("b"),
      Symbol("c"), Symbol("c"),
      Symbol("a"), Symbol("a"),
      Symbol("d"),
      Symbol("e"), Symbol("e"), Symbol("e"), Symbol("e")
    )
    val expected = List(
      List(Symbol("a"), Symbol("a"), Symbol("a"), Symbol("a")),
      List(Symbol("b")),
      List(Symbol("c"), Symbol("c")),
      List(Symbol("a"), Symbol("a")),
      List(Symbol("d")),
      List(Symbol("e"), Symbol("e"), Symbol("e"), Symbol("e"))
    )
    solver.pack(input) shouldBe expected
  }

  test("returns an empty list when given an empty list") {
    solver.pack(List.empty[Symbol]) shouldBe Nil
  }

  test("wraps a single-element list in a single group") {
    solver.pack(List(Symbol("x"))) shouldBe List(List(Symbol("x")))
  }

  test("produces one singleton group per element when there are no duplicates") {
    solver.pack(List(Symbol("a"), Symbol("b"), Symbol("c"))) shouldBe
      List(List(Symbol("a")), List(Symbol("b")), List(Symbol("c")))
  }

  test("produces a single group when the whole list is one repeated element") {
    solver.pack(List(Symbol("x"), Symbol("x"), Symbol("x"))) shouldBe
      List(List(Symbol("x"), Symbol("x"), Symbol("x")))
  }

  test("keeps non-adjacent equal runs as separate groups") {
    solver.pack(List(1, 1, 2, 2, 1)) shouldBe List(List(1, 1), List(2, 2), List(1))
  }

  test("works with strings as the element type") {
    solver.pack(List("a", "a", "b", "c", "c", "c")) shouldBe
      List(List("a", "a"), List("b"), List("c", "c", "c"))
  }

  test("handles a group of exactly two consecutive duplicates followed by a single element") {
    solver.pack(List(1, 1, 2)) shouldBe List(List(1, 1), List(2))
  }
}
