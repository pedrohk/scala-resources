package P01 {

  import org.scalatest.funsuite.AnyFunSuite

  class P06Test extends AnyFunSuite {

    test("works") {
      assert(P06.last(List(9, 8, 7)) == 7)
    }

    test("empty") {
      assertThrows[NoSuchElementException] {
        P06.last(Nil)
      }
    }
  }
}