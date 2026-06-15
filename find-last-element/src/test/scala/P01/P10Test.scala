package P01 {

  import org.scalatest.funsuite.AnyFunSuite

  class P10Test extends AnyFunSuite {

    test("works") {
      assert(P10.last(List(1, 3, 5, 7)) == 7)
    }

    test("empty") {
      assertThrows[NoSuchElementException] {
        P10.last(Nil)
      }
    }
  }
}