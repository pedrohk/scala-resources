package P03 {

  import org.scalatest.funsuite.AnyFunSuite

  class P10Test
    extends AnyFunSuite {

    test(
      "find"
    ) {

      assert(
        P10.nth(
          3,
          List(
            1,
            2,
            3,
            4,
            5
          )
        ) == 4
      )
    }

    test(
      "invalid"
    ) {

      assertThrows[
        IndexOutOfBoundsException
      ] {

        P10.nth(
          8,
          List(
            1
          )
        )
      }
    }
  }
}
