package P03 {

  import org.scalatest.funsuite.AnyFunSuite

  class P06Test
    extends AnyFunSuite {

    test(
      "find element"
    ) {

      assert(
        P06.nth(
          3,
          List(
            2,
            4,
            6,
            8,
            10
          )
        ) == 8
      )
    }

    test(
      "single value"
    ) {

      assert(
        P06.nth(
          0,
          List(
            9
          )
        ) == 9
      )
    }

    test(
      "overflow"
    ) {

      assertThrows[
        IndexOutOfBoundsException
      ] {

        P06.nth(
          3,
          List(
            1
          )
        )
      }
    }
  }
}