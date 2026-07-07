package P05 {

  object P10 {

    sealed trait Command[A]

    final case class Reverse[A](
                                 remaining: List[A],
                                 result: List[A]
                               ) extends Command[A]

    final case class Return[A](
                                value: List[A]
                              ) extends Command[A]

    def reverse[A](
                    list: List[A]
                  ): List[A] = {

      def execute(
                   command: Command[A]
                 ): List[A] = {

        command match {

          case Reverse(
            Nil,
            result
          ) => {

            execute(
              Return(
                result
              )
            )
          }

          case Reverse(
            head :: tail,
            result
          ) => {

            execute(
              Reverse(
                tail,
                head :: result
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
        Reverse(
          list,
          Nil
        )
      )
    }
  }
}