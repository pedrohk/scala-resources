package P05 {

  import org.scalatest.funsuite.AnyFunSuite

  class P06Test
    extends AnyFunSuite {

    test(
      "reverse empty list"
    ) {

      assert(
        P06.reverse(
          Nil
        ) == Nil
      )
    }

    test(
      "reverse one item"
    ) {

      assert(
        P06.reverse(
          List(
            100
          )
        ) == List(
          100
        )
      )
    }

    test(
      "reverse many items"
    ) {

      assert(
        P06.reverse(
          List(
            1,
            2,
            3,
            4
          )
        ) == List(
          4,
          3,
          2,
          1
        )
      )
    }
  }
}