package P04 {

  import org.scalatest.funsuite.AnyFunSuite

  class P01Test
    extends AnyFunSuite {

    test(
      "empty list"
    ) {

      assert(
        P01.length(
          Nil
        ) == 0
      )
    }

    test(
      "example list"
    ) {

      assert(
        P01.length(
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

    test(
      "single element"
    ) {

      assert(
        P01.length(
          List(
            99
          )
        ) == 1
      )
    }

    test(
      "string list"
    ) {

      assert(
        P01.length(
          List(
            "a",
            "b",
            "c"
          )
        ) == 3
      )
    }
  }
}