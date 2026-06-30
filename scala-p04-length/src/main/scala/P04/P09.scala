package P04 {

  object P09 {

    def length[A](
                   list: List[A]
                 ): Int = {

      def loop(
                remaining: List[A],
                continuation: Int => Int
              ): Int = {

        remaining match {

          case Nil => {

            continuation(
              0
            )
          }

          case _ :: tail => {

            loop(
              tail,
              value =>
                continuation(
                  value + 1
                )
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