package P03 {

  object P01 {

    def nth[A](
                index: Int,
                list: List[A]
              ): A = {

      if (index < 0) {
        throw new IndexOutOfBoundsException(
          "Negative index"
        )
      }

      list match {

        case Nil => {
          throw new IndexOutOfBoundsException(
            "Index out of range"
          )
        }

        case head :: tail => {

          if (index == 0) {
            head
          } else {
            nth(
              index - 1,
              tail
            )
          }
        }
      }
    }
  }
}