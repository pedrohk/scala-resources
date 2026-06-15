package P01 {

  import org.scalatest.funsuite.AnyFunSuite

  class P08Test extends AnyFunSuite {

    test("works") {
      assert(P08.last(List(1, 2)) == 2)
    }

    test("empty") {
      assertThrows[NoSuchElementException] {
        P08.last(Nil)
      }
    }
  }
}