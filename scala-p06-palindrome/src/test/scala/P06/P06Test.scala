package P06 {

  import org.scalatest.funsuite.AnyFunSuite

  class P06Test
    extends AnyFunSuite {

    test(
      "palindrome example"
    ) {

      assert(
        P06.isPalindrome(
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
      "non palindrome example"
    ) {

      assert(
        !P06.isPalindrome(
          List(
            1,
            2,
            3,
            5
          )
        )
      )
    }

    test(
      "two equal elements"
    ) {

      assert(
        P06.isPalindrome(
          List(
            "x",
            "x"
          )
        )
      )
    }
  }
}