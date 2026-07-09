package P06 {

  import org.scalatest.funsuite.AnyFunSuite

  class P04Test
    extends AnyFunSuite {

    test(
      "odd palindrome"
    ) {

      assert(
        P04.isPalindrome(
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
      "even palindrome"
    ) {

      assert(
        P04.isPalindrome(
          List(
            1,
            2,
            2,
            1
          )
        )
      )
    }

    test(
      "not palindrome"
    ) {

      assert(
        !P04.isPalindrome(
          List(
            1,
            2,
            3,
            4
          )
        )
      )
    }
  }
}