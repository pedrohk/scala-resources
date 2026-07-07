package P05 {

  import org.scalatest.funsuite.AnyFunSuite

  class P10Test
    extends AnyFunSuite {

    test(
      "reverse empty"
    ) {

      assert(
        P10.reverse(
          Nil
        ) == Nil
      )
    }

    test(
      "reverse single"
    ) {

      assert(
        P10.reverse(
          List(
            5
          )
        ) == List(
          5
        )
      )
    }

    test(
      "reverse example"
    ) {

      assert(
        P10.reverse(
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