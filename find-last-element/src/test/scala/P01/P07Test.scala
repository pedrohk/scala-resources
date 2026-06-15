package P01 {

  import org.scalatest.funsuite.AnyFunSuite

  class P07Test extends AnyFunSuite {

    test("works") {
      assert(P07.last(List(3, 2, 1)) == 1)
    }

    test("empty") {
      assertThrows[NoSuchElementException] {
        P07.last(Nil)
      }
    }
  }
}