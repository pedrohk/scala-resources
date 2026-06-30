package P04 {

  import org.scalatest.funsuite.AnyFunSuite

  class P08Test
    extends AnyFunSuite {

    test(
      "empty"
    ) {

      assert(
        P08.length(
          Nil
        ) == 0
      )
    }

    test(
      "single"
    ) {

      assert(
        P08.length(
          List(
            99
          )
        ) == 1
      )
    }

    test(
      "many"
    ) {

      assert(
        P08.length(
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