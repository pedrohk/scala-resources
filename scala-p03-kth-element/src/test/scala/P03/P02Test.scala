package P03 {

  import org.scalatest.funsuite.AnyFunSuite

  class P02Test
    extends AnyFunSuite {

    test(
      "middle element"
    ) {

      assert(
        P02.nth(
          2,
          List(
            10,
            20,
            30,
            40
          )
        ) == 30
      )
    }

    test(
      "last element"
    ) {

      assert(
        P02.nth(
          1,
          List(
            9,
            8
          )
        ) == 8
      )
    }

    test(
      "empty list"
    ) {

      assertThrows[
        IndexOutOfBoundsException
      ] {

        P02.nth(
          0,
          Nil
        )
      }
    }
  }
}