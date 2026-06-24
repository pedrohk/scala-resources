package P03 {

  object P09 {

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

      def loop(
                current: List[A],
                index: Int,
                continuation: A => A
              ): A = {

        current match {

          case Nil => {

            throw new IndexOutOfBoundsException(
              "Index out of range"
            )
          }

          case head :: tail => {

            if (
              index ==
                target
            ) {

              continuation(
                head
              )

            } else {

              loop(
                tail,
                index + 1,
                continuation
              )
            }
          }
        }
      }

      loop(
        list,
        0,
        value => value
      )
    }
  }
}