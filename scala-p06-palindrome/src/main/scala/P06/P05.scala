package P06 {

  object P05 {

    final class ReverseCursor[A](
                                  val remaining: List[A],
                                  val result: List[A]
                                )

    final class CompareCursor[A](
                                  val left: List[A],
                                  val right: List[A]
                                )

    private def reverse[A](
                            list: List[A]
                          ): List[A] = {

      def walk(
                cursor: ReverseCursor[A]
              ): List[A] = {

        cursor.remaining match {

          case Nil => {
            cursor.result
          }

          case head :: tail => {

            walk(
              new ReverseCursor(
                tail,
                head :: cursor.result
              )
            )
          }
        }
      }

      walk(
        new ReverseCursor(
          list,
          Nil
        )
      )
    }

    private def compare[A](
                            cursor: CompareCursor[A]
                          ): Boolean = {

      (cursor.left, cursor.right) match {

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
              new CompareCursor(
                leftTail,
                rightTail
              )
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
        new CompareCursor(
          list,
          reverse(
            list
          )
        )
      )
    }
  }
}