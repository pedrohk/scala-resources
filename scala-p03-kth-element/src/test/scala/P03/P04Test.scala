package P03 {

  import org.scalatest.funsuite.AnyFunSuite

  class P04Test
    extends AnyFunSuite {

    test(
      "find middle"
    ) {

      assert(
        P04.nth(
          2,
          List(
            1,
            1,
            2,
            3,
            5,
            8
          )
        ) == 2
      )
    }

    test(
      "first item"
    ) {

      assert(
        P04.nth(
          0,
          List(
            10
          )
        ) == 10
      )
    }

    test(
      "invalid index"
    ) {

      assertThrows[
        IndexOutOfBoundsException
      ] {

        P04.nth(
          5,
          List(
            1
          )
        )
      }
    }
  }
}