package P05 {

  import org.scalatest.funsuite.AnyFunSuite

  class P02Test
    extends AnyFunSuite {

    test(
      "reverse empty"
    ) {

      assert(
        P02.reverse(
          Nil
        ) == Nil
      )
    }

    test(
      "reverse integers"
    ) {

      assert(
        P02.reverse(
          List(
            10,
            20,
            30
          )
        ) == List(
          30,
          20,
          10
        )
      )
    }

    test(
      "reverse strings"
    ) {

      assert(
        P02.reverse(
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