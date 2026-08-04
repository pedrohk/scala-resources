package P11

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class P03Test extends AnyFunSuite with Matchers {

  private val solver = new P03()

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
      Symbol("b"),
      (2, Symbol("c")),
      (2, Symbol("a")),
      Symbol("d"),
      (4, Symbol("e"))
    )
    solver.encodeModified(input) shouldBe expected
  }

  test("returns an empty list when given an empty list") {
    solver.encodeModified(List.empty[Symbol]) shouldBe Nil
  }

  test("copies a single-element list as the bare element") {
    solver.encodeModified(List(Symbol("x"))) shouldBe List(Symbol("x"))
  }

  test("copies every element bare when there are no duplicates") {
    solver.encodeModified(List(Symbol("a"), Symbol("b"), Symbol("c"))) shouldBe
      List(Symbol("a"), Symbol("b"), Symbol("c"))
  }

  test("produces a single tuple when the whole list is one repeated element") {
    solver.encodeModified(List(Symbol("x"), Symbol("x"), Symbol("x"))) shouldBe
      List((3, Symbol("x")))
  }

  test("keeps non-adjacent equal runs as separate results") {
    solver.encodeModified(List(1, 1, 2, 2, 1)) shouldBe List((2, 1), (2, 2), 1)
  }

  test("works with strings as the element type") {
    solver.encodeModified(List("a", "a", "b", "c", "c", "c")) shouldBe
      List((2, "a"), "b", (3, "c"))
  }

  test("copies a bare element that sits between two runs") {
    solver.encodeModified(List(1, 2, 2, 2, 3)) shouldBe List(1, (3, 2), 3)
  }
}
