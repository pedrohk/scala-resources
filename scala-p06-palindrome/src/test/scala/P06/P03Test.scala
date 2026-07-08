package P06 {

  import org.scalatest.funsuite.AnyFunSuite

  class P03Test
    extends AnyFunSuite {

    test(
      "palindrome string list"
    ) {

      assert(
        P03.isPalindrome(
          List(
            "a",
            "b",
            "a"
          )
        )
      )
    }

    test(
      "non palindrome string list"
    ) {

      assert(
        !P03.isPalindrome(
          List(
            "a",
            "b",
            "c"
          )
        )
      )
    }

    test(
      "empty list"
    ) {

      assert(
        P03.isPalindrome(
          Nil
        )
      )
    }
  }
}