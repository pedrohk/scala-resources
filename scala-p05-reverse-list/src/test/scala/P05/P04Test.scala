package P05 {

  import org.scalatest.funsuite.AnyFunSuite

  class P04Test
    extends AnyFunSuite {

    test(
      "reverse empty list"
    ) {

      assert(
        P04.reverse(
          Nil
        ) == Nil
      )
    }

    test(
      "reverse single element"
    ) {

      assert(
        P04.reverse(
          List(
            7
          )
        ) == List(
          7
        )
      )
    }

    test(
      "reverse example list"
    ) {

      assert(
        P04.reverse(
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