package P01 {

  import org.scalatest.funsuite.AnyFunSuite

  class P02Test extends AnyFunSuite {

    test("works") {
      assert(P02.last(List(1, 2, 3)) == 3)
      assert(P02.last(List("x")) == "x")
    }

    test("throws") {
      assertThrows[NoSuchElementException] {
        P02.last(Nil)
      }
    }
  }
}