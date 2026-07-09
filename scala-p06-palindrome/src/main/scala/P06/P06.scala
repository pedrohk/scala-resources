package P06 {

  object P06 {

    private def reverse[A](
                            list: List[A]
                          ): List[A] = {

      var current =
        list

      var result: List[A] =
        Nil

      while (
        current match {

          case Nil => {
            false
          }

          case _ => {
            true
          }
        }
      ) {

        current match {

          case head :: tail => {

            result =
              head :: result

            current =
              tail
          }

          case Nil => {}
        }
      }

      result
    }

    private def compare[A](
                            left: List[A],
                            right: List[A]
                          ): Boolean = {

      (left, right) match {

        case (Nil, Nil) => {
          true
        }

        case (
          leftHead :: leftTail,
          rightHead :: rightTail
        ) => {

          if (
            leftHead == rightHead
          ) {

            compare(
              leftTail,
              rightTail
            )

          } else {
            false
          }
        }

        case _ => {
          false
        }
      }
    }

    def isPalindrome[A](
                         list: List[A]
                       ): Boolean = {

      compare(
        list,
        reverse(
          list
        )
      )
    }
  }
}