package P04 {

  import org.scalatest.funsuite.AnyFunSuite

  class P05Test
    extends AnyFunSuite {

    test(
      "empty"
    ) {

      assert(
        P05.length(
          Nil
        ) == 0
      )
    }

    test(
      "two elements"
    ) {

      assert(
        P05.length(
          List(
            "a",
            "b"
          )
        ) == 2
      )
    }

    test(
      "many elements"
    ) {

      assert(
        P05.length(
          List(
            1,
            2,
            3,
            4
          )
        ) == 4
      )
    }
  }
}