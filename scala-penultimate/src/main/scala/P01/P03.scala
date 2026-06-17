package P01 {

  object P03 {

    sealed trait State[A]

    final case class Walking[A](
                                 previous: Option[A],
                                 current: List[A]
                               ) extends State[A]

    final case class Done[A](
                              value: A
                            ) extends State[A]

    def penultimate[A](list: List[A]): A = {

      def execute(
                   state: State[A]
                 ): A = {

        state match {

          case Walking(_, Nil) => {
            throw new NoSuchElementException("List too short")
          }

          case Walking(None, _ :: Nil) => {
            throw new NoSuchElementException("List too short")
          }

          case Walking(Some(value), _ :: Nil) => {
            value
          }

          case Walking(previous, head :: tail) => {
            execute(
              Walking(
                Some(head),
                tail
              )
            )
          }

          case Done(value) => {
            value
          }
        }
      }

      execute(
        Walking(
          None,
          list
        )
      )
    }
  }
}