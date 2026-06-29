package P04 {

  object P05 {

    final class Cursor[A](
                           val remaining: List[A],
                           val count: Int
                         )

    def length[A](
                   list: List[A]
                 ): Int = {

      def walk(
                cursor: Cursor[A]
              ): Int = {

        cursor.remaining match {

          case Nil => {
            cursor.count
          }

          case _ :: tail => {

            walk(
              new Cursor(
                tail,
                cursor.count + 1
              )
            )
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