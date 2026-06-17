package P01 {

  object P02 {

    def penultimate[A](list: List[A]): A = {

      @annotation.tailrec
      def loop(
                current: List[A],
                previous: Option[A]
              ): A = {

        current match {

          case Nil => {
            throw new NoSuchElementException("List too short")
          }

          case head :: Nil => {
            previous match {
              case Some(value) => {
                value
              }

              case None => {
                throw new NoSuchElementException("List too short")
              }
            }
          }

          case head :: tail => {
            loop(tail, Some(head))
          }
        }
      }

      loop(list, None)
    }
  }
}