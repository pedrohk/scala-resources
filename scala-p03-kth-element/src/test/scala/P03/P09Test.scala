package P03 {

  import org.scalatest.funsuite.AnyFunSuite

  class P09Test
    extends AnyFunSuite {

    test(
      "works"
    ) {

      assert(
        P09.nth(
          0,
          List(
            99
          )
        ) == 99
      )
    }

    test(
      "empty"
    ) {

      assertThrows[
        IndexOutOfBoundsException
      ] {

        P09.nth(
          0,
          Nil
        )
      }
    }
  }
}
