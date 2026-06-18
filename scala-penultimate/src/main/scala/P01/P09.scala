package P01 {

  object P09 {

    def penultimate[A](
                        list: List[A]
                      ): A = {

      def loop(
                current: List[A],
                continuation: A => A
              ): A = {

        current match {

          case Nil => {
            throw new NoSuchElementException(
              "List too short"
            )
          }

          case value :: _ :: Nil => {
            continuation(
              value
            )
          }

          case _ :: tail => {

            loop(
              tail,
              continuation
            )
          }
        }
      }

      loop(
        list,
        value => value
      )
    }
  }
}