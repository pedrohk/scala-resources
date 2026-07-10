package P06 {

  object P09 {

    private def appendAtEnd[A](
                                list: List[A],
                                value: A
                              ): List[A] = {

      list match {

        case Nil => {
          value :: Nil
        }

        case head :: tail => {
          head :: appendAtEnd(
            tail,
            value
          )
        }
      }
    }

    private def reverseCps[A, B](
                                  list: List[A],
                                  continuation: List[A] => B
                                ): B = {

      list match {

        case Nil => {
          continuation(
            Nil
          )
        }

        case head :: tail => {

          reverseCps[A, B](
            tail,
            reversedTail =>
              continuation(
                appendAtEnd(
                  reversedTail,
                  head
                )
              )
          )
        }
      }
    }

    private def compareCps[A, B](
                                  left: List[A],
                                  right: List[A],
                                  continuation: Boolean => B
                                ): B = {

      (left, right) match {

        case (Nil, Nil) => {
          continuation(
            true
          )
        }

        case (
          leftHead :: leftTail,
          rightHead :: rightTail
        ) => {

          if (
            leftHead == rightHead
          ) {

            compareCps[A, B](
              leftTail,
              rightTail,
              result =>
                continuation(
                  result
                )
            )

          } else {

            continuation(
              false
            )
          }
        }

        case _ => {
          continuation(
            false
          )
        }
      }
    }

    def isPalindrome[A](
                         list: List[A]
                       ): Boolean = {

      reverseCps[A, Boolean](
        list,
        reversed =>
          compareCps[A, Boolean](
            list,
            reversed,
            result => result
          )
      )
    }
  }
}