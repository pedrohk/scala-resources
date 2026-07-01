package P04 {

  object P10 {

    sealed trait Command[A]

    final case class Count[A](
                               remaining: List[A],
                               total: Int
                             ) extends Command[A]

    final case class Return[A](
                                value: Int
                              ) extends Command[A]

    def length[A](
                   list: List[A]
                 ): Int = {

      def execute(
                   command: Command[A]
                 ): Int = {

        command match {

          case Count(
            Nil,
            total
          ) => {

            execute(
              Return(
                total
              )
            )
          }

          case Count(
            _ :: tail,
            total
          ) => {

            execute(
              Count(
                tail,
                total + 1
              )
            )
          }

          case Return(
            value
          ) => {

            value
          }
        }
      }

      execute(
        Count(
          list,
          0
        )
      )
    }
  }
}