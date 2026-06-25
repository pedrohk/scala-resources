package P03 {

  object P10 {

    sealed trait Command[A]

    final case class Advance[A](
                                 remaining: List[A],
                                 index: Int
                               ) extends Command[A]

    final case class Return[A](
                                value: A
                              ) extends Command[A]

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

      def execute(
                   command: Command[A]
                 ): A = {

        command match {

          case Advance(
            Nil,
            _
          ) => {

            throw new IndexOutOfBoundsException(
              "Index out of range"
            )
          }

          case Advance(
            head :: tail,
            current
          ) => {

            if (
              current ==
                target
            ) {

              execute(
                Return(
                  head
                )
              )

            } else {

              execute(
                Advance(
                  tail,
                  current + 1
                )
              )
            }
          }

          case Return(
            value
          ) => {

            value
          }
        }
      }

      execute(
        Advance(
          list,
          0
        )
      )
    }
  }
}