package P06 {

  import org.scalatest.funsuite.AnyFunSuite

  class P10Test
    extends AnyFunSuite {

    test(
      "returns true for empty list"
    ) {

      assert(
        P10.isPalindrome(
          Nil
        )
      )
    }

    test(
      "returns false for non palindrome"
    ) {

      assert(
        !P10.isPalindrome(
          List(
            1,
            2
          )
        )
      )
    }

    test(
      "returns true for palindrome example"
    ) {

      assert(
        P10.isPalindrome(
          List(
            1,
            2,
            3,
            2,
            1
          )
        )
      )
    }
  }
}