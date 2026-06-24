package P03 {

  import org.scalatest.funsuite.AnyFunSuite

  class P08Test
    extends AnyFunSuite {

    test(
      "strings"
    ) {

      assert(
        P08.nth(
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
      "negative"
    ) {

      assertThrows[
        IndexOutOfBoundsException
      ] {

        P08.nth(
          -1,
          List(
            1
          )
        )
      }
    }
  }
}