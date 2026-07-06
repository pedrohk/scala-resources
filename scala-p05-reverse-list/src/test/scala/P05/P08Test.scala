package P05 {

  import org.scalatest.funsuite.AnyFunSuite

  class P08Test
    extends AnyFunSuite {

    test(
      "reverse empty"
    ) {

      assert(
        P08.reverse(
          Nil
        ) == Nil
      )
    }

    test(
      "reverse single element"
    ) {

      assert(
        P08.reverse(
          List(
            9
          )
        ) == List(
          9
        )
      )
    }

    test(
      "reverse many"
    ) {

      assert(
        P08.reverse(
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