package P04 {

  object P06 {

    def length[A](
                   list: List[A]
                 ): Int = {

      var current =
        list

      var count =
        0

      while (
        current match {

          case Nil => {
            false
          }

          case _ => {
            true
          }
        }
      ) {

        current match {

          case _ :: tail => {

            count =
              count + 1

            current =
              tail
          }

          case Nil => {}
        }
      }

      count
    }
  }
}