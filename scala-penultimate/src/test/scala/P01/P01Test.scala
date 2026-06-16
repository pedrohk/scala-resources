package P01 {

  import org.scalatest.funsuite.AnyFunSuite

  class P01Test extends AnyFunSuite {

    test("find penultimate integer") {
      assert(
        P01.penultimate(List(1, 1, 2, 3, 5, 8)) == 5
      )
    }

    test("find penultimate string") {
      assert(
        P01.penultimate(List("a", "b", "c")) == "b"
      )
    }

    test("two elements") {
      assert(
        P01.penultimate(List(10, 20)) == 10
      )
    }

    test("single element throws") {
      assertThrows[NoSuchElementException] {
        P01.penultimate(List(1))
      }
    }

    test("empty list throws") {
      assertThrows[NoSuchElementException] {
        P01.penultimate(List.empty[Int])
      }
    }
  }
}