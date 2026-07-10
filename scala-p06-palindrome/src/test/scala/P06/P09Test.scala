package P06 {

  import org.scalatest.funsuite.AnyFunSuite

  class P09Test
    extends AnyFunSuite {

    test(
      "returns true for palindrome"
    ) {

      assert(
        P09.isPalindrome(
          List(
            9,
            8,
            9
          )
        )
      )
    }

    test(
      "returns false for non palindrome"
    ) {

      assert(
        !P09.isPalindrome(
          List(
            9,
            8,
            7
          )
        )
      )
    }

    test(
      "returns true for even palindrome"
    ) {

      assert(
        P09.isPalindrome(
          List(
            "x",
            "y",
            "y",
            "x"
          )
        )
      )
    }
  }
}