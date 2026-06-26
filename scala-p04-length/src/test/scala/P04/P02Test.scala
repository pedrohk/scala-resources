package P04 {

  import org.scalatest.funsuite.AnyFunSuite

  class P02Test
    extends AnyFunSuite {

    test(
      "empty list"
    ) {

      assert(
        P02.length(
          Nil
        ) == 0
      )
    }

    test(
      "two elements"
    ) {

      assert(
        P02.length(
          List(
            10,
            20
          )
        ) == 2
      )
    }

    test(
      "many elements"
    ) {

      assert(
        P02.length(
          List(
            1,
            2,
            3,
            4,
            5
          )
        ) == 5
      )
    }
  }
}
