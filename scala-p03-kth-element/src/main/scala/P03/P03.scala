package P03 {

  object P03 {

    sealed trait State[A]

    final case class Search[A](
                                position: Int,
                                remaining: List[A]
                              ) extends State[A]

    final case class Result[A](
                                value: A
                              ) extends State[A]

    def nth[A](
                index: Int,
                list: List[A]
              ): A = {

      if (index < 0) {
        throw new IndexOutOfBoundsException(
          "Negative index"
        )
      }

      def execute(
                   state: State[A]
                 ): A = {

        state match {

          case Search(
            _,
            Nil
          ) => {

            throw new IndexOutOfBoundsException(
              "Index out of range"
            )
          }

          case Search(
            position,
            head :: tail
          ) => {

            if (
              position == index
            ) {

              execute(
                Result(
                  head
                )
              )

            } else {

              execute(
                Search(
                  position + 1,
                  tail
                )
              )
            }
          }

          case Result(
            value
          ) => {

            value
          }
        }
      }

      execute(
        Search(
          0,
          list
        )
      )
    }
  }
}