package P04 {

  import org.scalatest.funsuite.AnyFunSuite

  class P03Test
    extends AnyFunSuite {

    test(
      "empty"
    ) {

      assert(
        P03.length(
          Nil
        ) == 0
      )
    }

    test(
      "single"
    ) {

      assert(
        P03.length(
          List(
            "x"
          )
        ) == 1
      )
    }

    test(
      "example"
    ) {

      assert(
        P03.length(
          List(
            1,
            1,
            2,
            3,
            5,
            8
          )
        ) == 6
      )
    }
  }
}