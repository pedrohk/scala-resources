package P06 {

  object P01 {

    private def reverse[A](
                            list: List[A]
                          ): List[A] = {

      def loop(
                remaining: List[A],
                result: List[A]
              ): List[A] = {

        remaining match {

          case Nil => {
            result
          }

          case head :: tail => {

            loop(
              tail,
              head :: result
            )
          }
        }
      }

      loop(
        list,
        Nil
      )
    }

    private def equalLists[A](
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

            equalLists(
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

      equalLists(
        list,
        reverse(
          list
        )
      )
    }
  }
}