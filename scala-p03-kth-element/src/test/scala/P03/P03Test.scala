package P03 {

  import org.scalatest.funsuite.AnyFunSuite

  class P03Test
    extends AnyFunSuite {

    test(
      "string list"
    ) {

      assert(
        P03.nth(
          1,
          List(
            "a",
            "b",
            "c"
          )
        ) == "b"
      )
    }

    test(
      "single item"
    ) {

      assert(
        P03.nth(
          0,
          List(
            99
          )
        ) == 99
      )
    }

    test(
      "overflow"
    ) {

      assertThrows[
        IndexOutOfBoundsException
      ] {

        P03.nth(
          10,
          List(
            1,
            2
          )
        )
      }
    }
  }
}