package P04 {

  object P01 {

    def length[A](
                   list: List[A]
                 ): Int = {

      list match {

        case Nil => {
          0
        }

        case _ :: tail => {
          1 + length(
            tail
          )
        }
      }
    }
  }
}