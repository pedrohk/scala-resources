package P03 {

  object P04 {

    def nth[A](
                index: Int,
                list: List[A]
              ): A = {

      if (index < 0) {
        throw new IndexOutOfBoundsException(
          "Negative index"
        )
      }

      def search(
                  remainingIndex: Int,
                  remaining: List[A]
                ): A = {

        remaining match {

          case Nil => {
            throw new IndexOutOfBoundsException(
              "Index out of range"
            )
          }

          case head :: tail => {

            if (
              remainingIndex == 0
            ) {
              head
            } else {

              search(
                remainingIndex - 1,
                tail
              )
            }
          }
        }
      }

      search(
        index,
        list
      )
    }
  }
}