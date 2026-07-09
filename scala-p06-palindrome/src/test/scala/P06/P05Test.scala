package P06 {

  import org.scalatest.funsuite.AnyFunSuite

  class P05Test
    extends AnyFunSuite {

    test(
      "empty list"
    ) {

      assert(
        P05.isPalindrome(
          Nil
        )
      )
    }

    test(
      "single element"
    ) {

      assert(
        P05.isPalindrome(
          List(
            8
          )
        )
      )
    }

    test(
      "non palindrome"
    ) {

      assert(
        !P05.isPalindrome(
          List(
            8,
            9
          )
        )
      )
    }
  }
}