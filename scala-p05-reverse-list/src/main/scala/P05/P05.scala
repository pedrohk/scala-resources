package P05 {

  object P05 {

    final class Cursor[A](
                           val remaining: List[A],
                           val result: List[A]
                         )

    def reverse[A](
                    list: List[A]
                  ): List[A] = {

      def walk(
                cursor: Cursor[A]
              ): List[A] = {

        cursor.remaining match {

          case Nil => {
            cursor.result
          }

          case head :: tail => {

            walk(
              new Cursor(
                tail,
                head :: cursor.result
              )
            )
          }
        }
      }

      walk(
        new Cursor(
          list,
          Nil
        )
      )
    }
  }
}