package P05 {

  import org.scalatest.funsuite.AnyFunSuite

  class P01Test
    extends AnyFunSuite {

    test(
      "reverse empty list"
    ) {

      assert(
        P01.reverse(
          Nil
        ) == Nil
      )
    }

    test(
      "reverse example list"
    ) {

      assert(
        P01.reverse(
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
      "reverse single element"
    ) {

      assert(
        P01.reverse(
          List(
            42
          )
        ) == List(
          42
        )
      )
    }
  }
}