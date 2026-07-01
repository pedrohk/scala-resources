package P04 {

  import org.scalatest.funsuite.AnyFunSuite

  class P10Test
    extends AnyFunSuite {

    test(
      "empty list"
    ) {

      assert(
        P10.length(
          Nil
        ) == 0
      )
    }

    test(
      "single element"
    ) {

      assert(
        P10.length(
          List(
            1
          )
        ) == 1
      )
    }

    test(
      "example list"
    ) {

      assert(
        P10.length(
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
