package P01 {

  object P10 {

    sealed trait Step[A]

    final case class Move[A](
                              previous: Option[A],
                              remaining: List[A]
                            ) extends Step[A]

    final case class Finish[A](
                                value: A
                              ) extends Step[A]

    def penultimate[A](
                        list: List[A]
                      ): A = {

      def run(
               state: Step[A]
             ): A = {

        state match {

          case Move(
            _,
            Nil
          ) => {

            throw new NoSuchElementException(
              "List too short"
            )
          }

          case Move(
            None,
            _ :: Nil
          ) => {

            throw new NoSuchElementException(
              "List too short"
            )
          }

          case Move(
            Some(
              value
            ),
            _ :: Nil
          ) => {

            value
          }

          case Move(
            _,
            head :: tail
          ) => {

            run(
              Move(
                Some(
                  head
                ),
                tail
              )
            )
          }

          case Finish(
            value
          ) => {

            value
          }
        }
      }

      run(
        Move(
          None,
          list
        )
      )
    }
  }
}