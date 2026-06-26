package P04 {

  object P03 {

    sealed trait State[A]

    final case class Counting[A](
                                  remaining: List[A],
                                  count: Int
                                ) extends State[A]

    final case class Done[A](
                              value: Int
                            ) extends State[A]

    def length[A](
                   list: List[A]
                 ): Int = {

      def execute(
                   state: State[A]
                 ): Int = {

        state match {

          case Counting(
            Nil,
            count
          ) => {

            execute(
              Done(
                count
              )
            )
          }

          case Counting(
            _ :: tail,
            count
          ) => {

            execute(
              Counting(
                tail,
                count + 1
              )
            )
          }

          case Done(
            value
          ) => {

            value
          }
        }
      }

      execute(
        Counting(
          list,
          0
        )
      )
    }
  }
}