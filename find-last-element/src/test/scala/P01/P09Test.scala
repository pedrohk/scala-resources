package P01 {

  import org.scalatest.funsuite.AnyFunSuite

  class P09Test extends AnyFunSuite {

    test("works") {
      assert(P09.last(List(99)) == 99)
    }

    test("empty") {
      assertThrows[NoSuchElementException] {
        P09.last(Nil)
      }
    }
  }
}
