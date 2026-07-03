package P05 {

  object P04 {

    def reverse[A](
                    list: List[A]
                  ): List[A] = {

      def build(
                 remaining: List[A],
                 result: List[A]
               ): List[A] = {

        remaining match {

          case Nil => {
            result
          }

          case head :: tail => {

            build(
              tail,
              head :: result
            )
          }
        }
      }

      build(
        list,
        Nil
      )
    }
  }
}