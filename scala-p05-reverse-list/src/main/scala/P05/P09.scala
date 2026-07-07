package P05 {

  object P09 {

    def reverse[A](
                    list: List[A]
                  ): List[A] = {

      def prependAll(
                      source: List[A],
                      target: List[A]
                    ): List[A] = {

        source match {

          case Nil => {
            target
          }

          case head :: tail => {
            head :: prependAll(
              tail,
              target
            )
          }
        }
      }

      def loop(
                remaining: List[A],
                continuation: List[A] => List[A]
              ): List[A] = {

        remaining match {

          case Nil => {
            continuation(
              Nil
            )
          }

          case head :: tail => {

            loop(
              tail,
              result =>
                continuation(
                  prependAll(
                    result,
                    head :: Nil
                  )
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