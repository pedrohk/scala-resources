package P05 {

  import org.scalatest.funsuite.AnyFunSuite

  class P05Test
    extends AnyFunSuite {

    test(
      "reverse empty"
    ) {

      assert(
        P05.reverse(
          Nil
        ) == Nil
      )
    }

    test(
      "reverse strings"
    ) {

      assert(
        P05.reverse(
          List(
            "x",
            "y",
            "z"
          )
        ) == List(
          "z",
          "y",
          "x"
        )
      )
    }

    test(
      "reverse integers"
    ) {

      assert(
        P05.reverse(
          List(
            10,
            20
          )
        ) == List(
          20,
          10
        )
      )
    }
  }
}