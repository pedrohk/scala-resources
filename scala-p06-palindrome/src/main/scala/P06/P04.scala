package P06 {

  object P04 {

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

    private def drop[A](
                         list: List[A],
                         count: Int
                       ): List[A] = {

      if (count <= 0) {
        list
      } else {

        list match {

          case Nil => {
            Nil
          }

          case _ :: tail => {
            drop(
              tail,
              count - 1
            )
          }
        }
      }
    }

    private def take[A](
                         list: List[A],
                         count: Int
                       ): List[A] = {

      if (count <= 0) {
        Nil
      } else {

        list match {

          case Nil => {
            Nil
          }

          case head :: tail => {
            head :: take(
              tail,
              count - 1
            )
          }
        }
      }
    }

    private def same[A](
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

            same(
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

      val size =
        length(
          list
        )

      val half =
        size / 2

      val left =
        take(
          list,
          half
        )

      val rightStart =
        if (size % 2 == 0) {
          half
        } else {
          half + 1
        }

      val right =
        drop(
          list,
          rightStart
        )

      same(
        left,
        reverse(
          right
        )
      )
    }
  }
}