package P03 {

  import org.scalatest.funsuite.AnyFunSuite

  class P07Test
    extends AnyFunSuite {

    test(
      "find kth"
    ) {

      assert(
        P07.nth(
          2,
          List(
            1,
            1,
            2,
            3
          )
        ) == 2
      )
    }

    test(
      "overflow"
    ) {

      assertThrows[
        IndexOutOfBoundsException
      ] {

        P07.nth(
          10,
          List(
            1
          )
        )
      }
    }
  }
}