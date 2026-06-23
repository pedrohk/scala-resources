package P03 {

  import org.scalatest.funsuite.AnyFunSuite

  class P05Test
    extends AnyFunSuite {

    test(
      "find string"
    ) {

      assert(
        P05.nth(
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
      "negative index"
    ) {

      assertThrows[
        IndexOutOfBoundsException
      ] {

        P05.nth(
          -1,
          List(
            1
          )
        )
      }
    }

    test(
      "empty"
    ) {

      assertThrows[
        IndexOutOfBoundsException
      ] {

        P05.nth(
          0,
          Nil
        )
      }
    }
  }
}