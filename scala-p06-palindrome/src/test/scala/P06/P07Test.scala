package P06 {

  import org.scalatest.funsuite.AnyFunSuite

  class P07Test
    extends AnyFunSuite {

    test(
      "returns true for odd palindrome"
    ) {

      assert(
        P07.isPalindrome(
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

    test(
      "returns true for even palindrome"
    ) {

      assert(
        P07.isPalindrome(
          List(
            "a",
            "b",
            "b",
            "a"
          )
        )
      )
    }

    test(
      "returns false for non palindrome"
    ) {

      assert(
        !P07.isPalindrome(
          List(
            1,
            2,
            3
          )
        )
      )
    }
  }
}