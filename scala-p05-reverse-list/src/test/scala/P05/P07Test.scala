package P05 {

  import org.scalatest.funsuite.AnyFunSuite

  class P07Test
    extends AnyFunSuite {

    test(
      "reverse empty list"
    ) {

      assert(
        P07.reverse(
          Nil
        ) == Nil
      )
    }

    test(
      "reverse example list"
    ) {

      assert(
        P07.reverse(
          List(
            1,
            1,
            2,
            3,
            5,
            8
          )
        ) == List(
          8,
          5,
          3,
          2,
          1,
          1
        )
      )
    }

    test(
      "reverse strings"
    ) {

      assert(
        P07.reverse(
          List(
            "a",
            "b",
            "c"
          )
        ) == List(
          "c",
          "b",
          "a"
        )
      )
    }
  }
}