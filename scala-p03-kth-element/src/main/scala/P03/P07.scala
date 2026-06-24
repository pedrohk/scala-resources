package P03 {

  object P07 {

    sealed trait State[A]

    final case class Visit[A](
                               index: Int,
                               remaining: List[A]
                             ) extends State[A]

    final case class Found[A](
                               value: A
                             ) extends State[A]

    def nth[A](
                target: Int,
                list: List[A]
              ): A = {

      if (target < 0) {
        throw new IndexOutOfBoundsException(
          "Negative index"
        )
      }

      def run(
               state: State[A]
             ): A = {

        state match {

          case Visit(
            _,
            Nil
          ) => {

            throw new IndexOutOfBoundsException(
              "Index out of range"
            )
          }

          case Visit(
            current,
            head :: tail
          ) => {

            if (
              current ==
                target
            ) {

              run(
                Found(
                  head
                )
              )

            } else {

              run(
                Visit(
                  current + 1,
                  tail
                )
              )
            }
          }

          case Found(
            value
          ) => {

            value
          }
        }
      }

      run(
        Visit(
          0,
          list
        )
      )
    }
  }
}