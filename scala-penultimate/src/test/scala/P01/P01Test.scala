package P01 {

  import org.scalatest.funsuite.AnyFunSuite

  class P01Test extends AnyFunSuite {

    test("find penultimate integer") {
      assert(
        P01.penultimate(List(1, 1, 2, 3, 5, 8)) == 5
      )
      assert(
        P02.penultimate(List(1, 1, 2, 3, 5, 8)) == 5
      )
      assert(
        P03.penultimate(List(1, 1, 2, 3, 5, 8)) == 5
      )
      assert(
        P04.penultimate(List(1, 1, 2, 3, 5, 8)) == 5
      )
      assert(
        P05.penultimate(List(1, 1, 2, 3, 5, 8)) == 5
      )
      assert(
        P06.penultimate(List(1, 1, 2, 3, 5, 8)) == 5
      )
      assert(
        P07.penultimate(List(1, 1, 2, 3, 5, 8)) == 5
      )
    }

    test("find penultimate string") {
      assert(
        P01.penultimate(List("a", "b", "c")) == "b"
      )
      assert(
        P02.penultimate(List("a", "b", "c")) == "b"
      )
      assert(
        P03.penultimate(List("a", "b", "c")) == "b"
      )
      assert(
        P04.penultimate(List("a", "b", "c")) == "b"
      )
      assert(
        P05.penultimate(List("a", "b", "c")) == "b"
      )
      assert(
        P06.penultimate(List("a", "b", "c")) == "b"
      )
      assert(
        P07.penultimate(List("a", "b", "c")) == "b"
      )
    }

    test("two elements") {
      assert(
        P01.penultimate(List(10, 20)) == 10
      )
      assert(
        P02.penultimate(List(10, 20)) == 10
      )
      assert(
        P03.penultimate(List(10, 20)) == 10
      )
      assert(
        P04.penultimate(List(10, 20)) == 10
      )
      assert(
        P05.penultimate(List(10, 20)) == 10
      )
      assert(
        P06.penultimate(List(10, 20)) == 10
      )
      assert(
        P07.penultimate(List(10, 20)) == 10
      )
    }

    test("single element throws") {
      assertThrows[NoSuchElementException] {
        P01.penultimate(List(1))
      }
      assertThrows[NoSuchElementException] {
        P02.penultimate(List(1))
      }
      assertThrows[NoSuchElementException] {
        P03.penultimate(List(1))
      }
      assertThrows[NoSuchElementException] {
        P04.penultimate(List(1))
      }
      assertThrows[NoSuchElementException] {
        P05.penultimate(List(1))
      }
      assertThrows[NoSuchElementException] {
        P06.penultimate(List(1))
      }
      assert(
        P07.penultimate(List(1, 2)) == 1
      )
    }

    test("empty list throws") {
      assertThrows[NoSuchElementException] {
        P01.penultimate(List.empty[Int])
      }
      assertThrows[NoSuchElementException] {
        P02.penultimate(List.empty[Int])
      }
      assertThrows[NoSuchElementException] {
        P03.penultimate(List.empty[Int])
      }
      assertThrows[NoSuchElementException] {
        P04.penultimate(List.empty[Int])
      }
      assertThrows[NoSuchElementException] {
        P05.penultimate(List.empty[Int])
      }
      assertThrows[NoSuchElementException] {
        P06.penultimate(List.empty[Int])
      }
      assertThrows[NoSuchElementException] {
        P07.penultimate(List.empty[Int])
      }
    }
  }
}