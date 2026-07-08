package P06 {

  import org.scalatest.funsuite.AnyFunSuite

  class P02Test
    extends AnyFunSuite {

    test(
      "odd palindrome"
    ) {

      assert(
        P02.isPalindrome(
          List(
            1,
            2,
            1
          )
        )
      )
    }

    test(
      "single element"
    ) {

      assert(
        P02.isPalindrome(
          List(
            99
          )
        )
      )
    }

    test(
      "not palindrome"
    ) {

      assert(
        !P02.isPalindrome(
          List(
            1,
            2,
            4
          )
        )
      )
    }
  }
}