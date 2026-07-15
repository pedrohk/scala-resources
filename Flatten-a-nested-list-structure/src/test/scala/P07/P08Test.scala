package P07

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class P08Test extends AnyFunSuite with Matchers {

  private val solver = new P08()

  test("flattens the example from the problem statement") {
    solver.flatten(List(List(1, 1), 2, List(3, List(5, 8)))) shouldBe List(1, 1, 2, 3, 5, 8)
  }

  test("returns an empty list when given an empty list") {
    solver.flatten(Nil) shouldBe Nil
  }

  test("returns the same list when it is already flat") {
    solver.flatten(List(1, 2, 3, 4)) shouldBe List(1, 2, 3, 4)
  }

  test("handles deeply nested lists") {
    solver.flatten(List(List(List(List(1))), 2, List(List(3)))) shouldBe List(1, 2, 3)
  }

  test("handles a single nested element") {
    solver.flatten(List(List(42))) shouldBe List(42)
  }

  test("ignores empty nested lists") {
    solver.flatten(List(List(), List(List(), List()), 1)) shouldBe List(1)
  }

  test("handles mixed types such as strings and doubles") {
    solver.flatten(List(1, List("a", "b"), List(List(2.5)))) shouldBe List(1, "a", "b", 2.5)
  }
}
