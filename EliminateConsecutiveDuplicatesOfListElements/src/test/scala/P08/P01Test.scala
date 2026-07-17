package P08

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class P01Test extends AnyFunSuite with Matchers {

  private val solver = new P01()

  test("compresses the example from the problem statement") {
    solver.compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) shouldBe
      List('a, 'b, 'c, 'a, 'd, 'e)
  }

  test("returns an empty list when given an empty list") {
    solver.compress(List.empty[Symbol]) shouldBe Nil
  }

  test("returns the same single-element list unchanged") {
    solver.compress(List('a)) shouldBe List('a)
  }

  test("leaves a list with no consecutive duplicates unchanged") {
    solver.compress(List('a, 'b, 'c, 'd)) shouldBe List('a, 'b, 'c, 'd)
  }

  test("collapses a list made entirely of one repeated element") {
    solver.compress(List('x, 'x, 'x, 'x, 'x)) shouldBe List('x)
  }

  test("handles alternating runs of duplicates") {
    solver.compress(List(1, 1, 2, 2, 1, 1, 3)) shouldBe List(1, 2, 1, 3)
  }

  test("works with strings as the element type") {
    solver.compress(List("a", "a", "b", "b", "b", "c")) shouldBe List("a", "b", "c")
  }

  test("does not merge non-adjacent duplicates") {
    solver.compress(List(1, 2, 1, 2, 1)) shouldBe List(1, 2, 1, 2, 1)
  }
}
