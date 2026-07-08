package P06 {

  object P02 {

    private def length[A](
                           list: List[A]
                         ): Int = {

      list match {

        case Nil => {
          0
        }

        case _ :: tail => {
          1 + length(
            tail
          )
        }
      }
    }

    private def nth[A](
                        index: Int,
                        list: List[A]
                      ): A = {

      if (index < 0) {
        throw new IndexOutOfBoundsException(
          "Negative index"
        )
      }

      list match {

        case Nil => {
          throw new IndexOutOfBoundsException(
            "Index out of range"
          )
        }

        case head :: tail => {

          if (index == 0) {
            head
          } else {
            nth(
              index - 1,
              tail
            )
          }
        }
      }
    }

    private def compare[A](
                            list: List[A],
                            left: Int,
                            right: Int
                          ): Boolean = {

      if (left >= right) {
        true
      } else if (
        nth(left, list) ==
          nth(right, list)
      ) {

        compare(
          list,
          left + 1,
          right - 1
        )

      } else {
        false
      }
    }

    def isPalindrome[A](
                         list: List[A]
                       ): Boolean = {

      val size =
        length(
          list
        )

      compare(
        list,
        0,
        size - 1
      )
    }
  }
}