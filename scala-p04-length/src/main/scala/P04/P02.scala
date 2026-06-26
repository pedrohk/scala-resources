package P04 {

  object P02 {

    def length[A](
                   list: List[A]
                 ): Int = {

      @annotation.tailrec
      def loop(
                remaining: List[A],
                count: Int
              ): Int = {

        remaining match {

          case Nil => {
            count
          }

          case _ :: tail => {

            loop(
              tail,
              count + 1
            )
          }
        }
      }

      loop(
        list,
        0
      )
    }
  }
}