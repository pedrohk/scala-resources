package P04 {

  object P04 {

    def length[A](
                   list: List[A]
                 ): Int = {

      def count(
                 remaining: List[A],
                 total: Int
               ): Int = {

        remaining match {

          case Nil => {
            total
          }

          case _ :: tail => {

            count(
              tail,
              total + 1
            )
          }
        }
      }

      count(
        list,
        0
      )
    }
  }
}