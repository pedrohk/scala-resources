package P01 {

  object P04 {

    def penultimate[A](list: List[A]): A = {

      def search(
                  previous: Option[A],
                  current: List[A]
                ): A = {

        current match {

          case Nil => {
            throw new NoSuchElementException(
              "List too short"
            )
          }

          case head :: Nil => {

            previous match {

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
            search(
              Some(head),
              tail
            )
          }
        }
      }

      search(
        None,
        list
      )
    }
  }
}