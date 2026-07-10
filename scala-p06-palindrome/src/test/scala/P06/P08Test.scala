package P06 {

  import org.scalatest.funsuite.AnyFunSuite

  class P08Test
    extends AnyFunSuite {

    test(
      "returns true for empty list"
    ) {

      assert(
        P08.isPalindrome(
          Nil
        )
      )
    }

    test(
      "returns true for single element"
    ) {

      assert(
        P08.isPalindrome(
          List(
            42
          )
        )
      )
    }

    test(
      "returns false for different ends"
    ) {

      assert(
        !P08.isPalindrome(
          List(
            1,
            2,
            1,
            3
          )
        )
      )
    }
  }
}