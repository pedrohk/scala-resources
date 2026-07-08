package P06 {

  import org.scalatest.funsuite.AnyFunSuite

  class P01Test
    extends AnyFunSuite {

    test(
      "odd palindrome"
    ) {

      assert(
        P01.isPalindrome(
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
        P01.isPalindrome(
          List(
            4,
            5,
            5,
            4
          )
        )
      )
    }

    test(
      "not palindrome"
    ) {

      assert(
        !P01.isPalindrome(
          List(
            1,
            2,
            3
          )
        )
      )
    }

    test(
      "empty list"
    ) {

      assert(
        P01.isPalindrome(
          Nil
        )
      )
    }
  }
}