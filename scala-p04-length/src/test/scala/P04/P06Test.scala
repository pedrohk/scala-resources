package P04 {

  import org.scalatest.funsuite.AnyFunSuite

  class P06Test
    extends AnyFunSuite {

    test(
      "empty"
    ) {

      assert(
        P06.length(
          Nil
        ) == 0
      )
    }

    test(
      "single"
    ) {

      assert(
        P06.length(
          List(
            42
          )
        ) == 1
      )
    }

    test(
      "example"
    ) {

      assert(
        P06.length(
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