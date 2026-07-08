package P06 {

  object P03 {

    sealed trait ReverseState[A]

    final case class Reversing[A](
                                   remaining: List[A],
                                   result: List[A]
                                 ) extends ReverseState[A]

    final case class Reversed[A](
                                  value: List[A]
                                ) extends ReverseState[A]

    private def reverse[A](
                            list: List[A]
                          ): List[A] = {

      def execute(
                   state: ReverseState[A]
                 ): List[A] = {

        state match {

          case Reversing(
            Nil,
            result
          ) => {

            execute(
              Reversed(
                result
              )
            )
          }

          case Reversing(
            head :: tail,
            result
          ) => {

            execute(
              Reversing(
                tail,
                head :: result
              )
            )
          }

          case Reversed(
            value
          ) => {

            value
          }
        }
      }

      execute(
        Reversing(
          list,
          Nil
        )
      )
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