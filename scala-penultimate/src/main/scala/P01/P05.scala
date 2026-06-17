package P01 {

  object P05 {

    final class Cursor[A](
                           val previous: Option[A],
                           val current: List[A]
                         )

    def penultimate[A](
                        list: List[A]
                      ): A = {

      def walk(
                cursor: Cursor[A]
              ): A = {

        cursor.current match {

          case Nil => {
            throw new NoSuchElementException(
              "List too short"
            )
          }

          case head :: Nil => {

            cursor.previous match {

              case Some(value) => {
                value
              }

              case None => {
                throw new NoSuchElementException(
                  "List too short"
                )
              }
            }
          }

          case head :: tail => {

            walk(
              new Cursor(
                Some(head),
                tail
              )
            )
          }
        }
      }

      walk(
        new Cursor(
          None,
          list
        )
      )
    }
  }
}