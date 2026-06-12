package P01 {

  import org.scalatest.funsuite.AnyFunSuite

  class P03Test extends AnyFunSuite {

    test("works") {
      assert(P03.last(List(10, 20, 30)) == 30)
    }

    test("empty") {
      assertThrows[NoSuchElementException] {
        P03.last(Nil)
      }
    }
  }
}