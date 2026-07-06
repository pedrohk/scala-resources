package P05 {

  object P07 {

    sealed trait State[A]

    final case class Reversing[A](
                                   remaining: List[A],
                                   result: List[A]
                                 ) extends State[A]

    final case class Finished[A](
                                  value: List[A]
                                ) extends State[A]

    def reverse[A](
                    list: List[A]
                  ): List[A] = {

      def run(
               state: State[A]
             ): List[A] = {

        state match {

          case Reversing(
            Nil,
            result
          ) => {

            run(
              Finished(
                result
              )
            )
          }

          case Reversing(
            head :: tail,
            result
          ) => {

            run(
              Reversing(
                tail,
                head :: result
              )
            )
          }

          case Finished(
            value
          ) => {

            value
          }
        }
      }

      run(
        Reversing(
          list,
          Nil
        )
      )
    }
  }
}