package P03 {

  object P06 {

    def nth[A](
                target: Int,
                list: List[A]
              ): A = {

      if (
        target < 0
      ) {

        throw new IndexOutOfBoundsException(
          "Negative index"
        )
      }

      var current =
        list

      var position =
        0

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

            if (
              position ==
                target
            ) {

              return head
            }

            position =
              position + 1

            current =
              tail
          }

          case _ => {}
        }
      }

      throw new IndexOutOfBoundsException(
        "Index out of range"
      )
    }
  }
}