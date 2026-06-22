package P03 {

  import org.scalatest.funsuite.AnyFunSuite

  class P01Test
    extends AnyFunSuite {

    test(
      "find kth integer"
    ) {

      assert(
        P01.nth(
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
      "first element"
    ) {

      assert(
        P01.nth(
          0,
          List(
            7,
            8
          )
        ) == 7
      )
    }

    test(
      "negative index"
    ) {

      assertThrows[
        IndexOutOfBoundsException
      ] {

        P01.nth(
          -1,
          List(
            1
          )
        )
      }
    }

    test(
      "out of bounds"
    ) {

      assertThrows[
        IndexOutOfBoundsException
      ] {

        P01.nth(
          5,
          List(
            1,
            2
          )
        )
      }
    }
  }
}