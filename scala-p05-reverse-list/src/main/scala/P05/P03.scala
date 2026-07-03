package P05 {

  object P03 {

    sealed trait State[A]

    final case class Reversing[A](
                                   remaining: List[A],
                                   result: List[A]
                                 ) extends State[A]

    final case class Done[A](
                              value: List[A]
                            ) extends State[A]

    def reverse[A](
                    list: List[A]
                  ): List[A] = {

      def execute(
                   state: State[A]
                 ): List[A] = {

        state match {

          case Reversing(
            Nil,
            result
          ) => {

            execute(
              Done(
                result
              )
            )
          }

          case Reversing(
            head :: tail,
            result
          ) => {

            execute(
              Reversing(
                tail,
                head :: result
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
        Reversing(
          list,
          Nil
        )
      )
    }
  }
}