package P01 {

  import org.scalatest.funsuite.AnyFunSuite

  class P01Test extends AnyFunSuite {

    test("find last integer") {
      assert(P01.last(List(1, 1, 2, 3, 5, 8)) == 8)
    }

    test("find last string") {
      assert(P01.last(List("a", "b", "c")) == "c")
    }

    test("single element") {
      assert(P01.last(List(42)) == 42)
    }

    test("empty list") {
      assertThrows[NoSuchElementException] {
        P01.last(List.empty[Int])
      }
    }
  }
}