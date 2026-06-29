package P04 {

  import org.scalatest.funsuite.AnyFunSuite

  class P04Test
    extends AnyFunSuite {

    test(
      "empty list"
    ) {

      assert(
        P04.length(
          Nil
        ) == 0
      )
    }

    test(
      "single element"
    ) {

      assert(
        P04.length(
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
        P04.length(
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