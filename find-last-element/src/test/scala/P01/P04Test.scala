package P01 {

  import org.scalatest.funsuite.AnyFunSuite

  class P04Test extends AnyFunSuite {

    test("works") {
      assert(P04.last(List(5, 7, 9)) == 9)
    }

    test("empty") {
      assertThrows[NoSuchElementException] {
        P04.last(Nil)
      }
    }
  }
}