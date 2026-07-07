package P05 {

  import org.scalatest.funsuite.AnyFunSuite

  class P09Test
    extends AnyFunSuite {

    test(
      "reverse empty list"
    ) {

      assert(
        P09.reverse(
          Nil
        ) == Nil
      )
    }

    test(
      "reverse one value"
    ) {

      assert(
        P09.reverse(
          List(
            77
          )
        ) == List(
          77
        )
      )
    }

    test(
      "reverse integers"
    ) {

      assert(
        P09.reverse(
          List(
            1,
            2,
            3
          )
        ) == List(
          3,
          2,
          1
        )
      )
    }
  }
}