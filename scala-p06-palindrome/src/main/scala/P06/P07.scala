package P06 {

  object P07 {

    sealed trait ReverseState[A]

    final case class Reversing[A](
                                   remaining: List[A],
                                   result: List[A]
                                 ) extends ReverseState[A]

    final case class Reversed[A](
                                  value: List[A]
                                ) extends ReverseState[A]

    sealed trait CompareState[A]

    final case class Comparing[A](
                                   left: List[A],
                                   right: List[A]
                                 ) extends CompareState[A]

    final case class ComparisonDone[A](
                                        value: Boolean
                                      ) extends CompareState[A]

    private def reverse[A](
                            list: List[A]
                          ): List[A] = {

      def runReverse(
                      state: ReverseState[A]
                    ): List[A] = {

        state match {

          case Reversing(
            Nil,
            result
          ) => {

            runReverse(
              Reversed(
                result
              )
            )
          }

          case Reversing(
            head :: tail,
            result
          ) => {

            runReverse(
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

      runReverse(
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

      def runCompare(
                      state: CompareState[A]
                    ): Boolean = {

        state match {

          case Comparing(
            Nil,
            Nil
          ) => {

            runCompare(
              ComparisonDone(
                true
              )
            )
          }

          case Comparing(
            leftHead :: leftTail,
            rightHead :: rightTail
          ) => {

            if (
              leftHead == rightHead
            ) {

              runCompare(
                Comparing(
                  leftTail,
                  rightTail
                )
              )

            } else {

              runCompare(
                ComparisonDone(
                  false
                )
              )
            }
          }

          case Comparing(
            _,
            _
          ) => {

            runCompare(
              ComparisonDone(
                false
              )
            )
          }

          case ComparisonDone(
            value
          ) => {
            value
          }
        }
      }

      runCompare(
        Comparing(
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