package P06 {

  object P10 {

    sealed trait ReverseCommand[A]

    final case class Reverse[A](
                                 remaining: List[A],
                                 result: List[A]
                               ) extends ReverseCommand[A]

    final case class ReverseDone[A](
                                     value: List[A]
                                   ) extends ReverseCommand[A]

    sealed trait CompareCommand[A]

    final case class Compare[A](
                                 left: List[A],
                                 right: List[A]
                               ) extends CompareCommand[A]

    final case class CompareDone[A](
                                     value: Boolean
                                   ) extends CompareCommand[A]

    private def reverse[A](
                            list: List[A]
                          ): List[A] = {

      def executeReverse(
                          command: ReverseCommand[A]
                        ): List[A] = {

        command match {

          case Reverse(
            Nil,
            result
          ) => {

            executeReverse(
              ReverseDone(
                result
              )
            )
          }

          case Reverse(
            head :: tail,
            result
          ) => {

            executeReverse(
              Reverse(
                tail,
                head :: result
              )
            )
          }

          case ReverseDone(
            value
          ) => {
            value
          }
        }
      }

      executeReverse(
        Reverse(
          list,
          Nil
        )
      )
    }

    private def compare[A](
                            left: List[A],
                            right: List[A]
                          ): Boolean = {

      def executeCompare(
                          command: CompareCommand[A]
                        ): Boolean = {

        command match {

          case Compare(
            Nil,
            Nil
          ) => {

            executeCompare(
              CompareDone(
                true
              )
            )
          }

          case Compare(
            leftHead :: leftTail,
            rightHead :: rightTail
          ) => {

            if (
              leftHead == rightHead
            ) {

              executeCompare(
                Compare(
                  leftTail,
                  rightTail
                )
              )

            } else {

              executeCompare(
                CompareDone(
                  false
                )
              )
            }
          }

          case Compare(
            _,
            _
          ) => {

            executeCompare(
              CompareDone(
                false
              )
            )
          }

          case CompareDone(
            value
          ) => {
            value
          }
        }
      }

      executeCompare(
        Compare(
          left,
          right
        )
      )
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