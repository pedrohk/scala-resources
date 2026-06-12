package P01 {

  import org.scalatest.funsuite.AnyFunSuite

  class P05Test extends AnyFunSuite {

    test("works") {
      assert(P05.last(List(2, 4, 6)) == 6)
    }

    test("empty") {
      assertThrows[NoSuchElementException] {
        P05.last(Nil)
      }
    }
  }
}