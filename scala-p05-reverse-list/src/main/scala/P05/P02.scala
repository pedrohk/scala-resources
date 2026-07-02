package P05 {

  object P02 {

    def reverse[A](
                    list: List[A]
                  ): List[A] = {

      @annotation.tailrec
      def loop(
                remaining: List[A],
                result: List[A]
              ): List[A] = {

        remaining match {

          case Nil => {
            result
          }

          case head :: tail => {

            loop(
              tail,
              head :: result
            )
          }
        }
      }

      loop(
        list,
        Nil
      )
    }
  }
}