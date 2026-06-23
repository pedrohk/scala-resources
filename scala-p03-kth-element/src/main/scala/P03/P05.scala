package P03 {

  object P05 {

    final class Cursor[A](
                           val current: List[A],
                           val index: Int
                         )

    def nth[A](
                target: Int,
                list: List[A]
              ): A = {

      if (target < 0) {
        throw new IndexOutOfBoundsException(
          "Negative index"
        )
      }

      def walk(
                cursor: Cursor[A]
              ): A = {

        cursor.current match {

          case Nil => {
            throw new IndexOutOfBoundsException(
              "Index out of range"
            )
          }

          case head :: tail => {

            if (
              cursor.index ==
                target
            ) {

              head

            } else {

              walk(
                new Cursor(
                  tail,
                  cursor.index + 1
                )
              )
            }
          }
        }
      }

      walk(
        new Cursor(
          list,
          0
        )
      )
    }
  }
}