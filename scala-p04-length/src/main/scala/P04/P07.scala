package P04 {

  object P07 {

    sealed trait State[A]

    final case class Counting[A](
                                  remaining: List[A],
                                  count: Int
                                ) extends State[A]

    final case class Finished[A](
                                  value: Int
                                ) extends State[A]

    def length[A](
                   list: List[A]
                 ): Int = {

      def run(
               state: State[A]
             ): Int = {

        state match {

          case Counting(
            Nil,
            count
          ) => {

            run(
              Finished(
                count
              )
            )
          }

          case Counting(
            _ :: tail,
            count
          ) => {

            run(
              Counting(
                tail,
                count + 1
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
        Counting(
          list,
          0
        )
      )
    }
  }
}