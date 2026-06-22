package P03 {

  object P02 {

    def nth[A](
                index: Int,
                list: List[A]
              ): A = {

      if (index < 0) {
        throw new IndexOutOfBoundsException(
          "Negative index"
        )
      }

      @annotation.tailrec
      def loop(
                remaining: List[A],
                position: Int
              ): A = {

        remaining match {

          case Nil => {
            throw new IndexOutOfBoundsException(
              "Index out of range"
            )
          }

          case head :: tail => {

            if (
              position == index
            ) {
              head
            } else {
              loop(
                tail,
                position + 1
              )
            }
          }
        }
      }

      loop(
        list,
        0
      )
    }
  }
}