package P04 {

  import org.scalatest.funsuite.AnyFunSuite

  class P09Test
    extends AnyFunSuite {

    test(
      "empty"
    ) {

      assert(
        P09.length(
          Nil
        ) == 0
      )
    }

    test(
      "two values"
    ) {

      assert(
        P09.length(
          List(
            7,
            8
          )
        ) == 2
      )
    }

    test(
      "example"
    ) {

      assert(
        P09.length(
          List(
            1,
            1,
            2,
            3,
            5,
            8
          )
        ) == 6
      )
    }
  }
}