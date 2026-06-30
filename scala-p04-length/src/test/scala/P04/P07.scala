package P04 {

  import org.scalatest.funsuite.AnyFunSuite

  class P07Test
    extends AnyFunSuite {

    test(
      "empty list"
    ) {

      assert(
        P07.length(
          Nil
        ) == 0
      )
    }

    test(
      "example list"
    ) {

      assert(
        P07.length(
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
      "string list"
    ) {

      assert(
        P07.length(
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