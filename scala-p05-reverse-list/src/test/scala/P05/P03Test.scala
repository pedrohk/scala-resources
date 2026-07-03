package P05 {

  import org.scalatest.funsuite.AnyFunSuite

  class P03Test
    extends AnyFunSuite {

    test(
      "reverse empty list"
    ) {

      assert(
        P03.reverse(
          Nil
        ) == Nil
      )
    }

    test(
      "reverse one element"
    ) {

      assert(
        P03.reverse(
          List(
            99
          )
        ) == List(
          99
        )
      )
    }

    test(
      "reverse example"
    ) {

      assert(
        P03.reverse(
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
  }
}