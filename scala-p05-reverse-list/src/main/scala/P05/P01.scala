package P05 {

  object P01 {

    private def append[A](
                           list: List[A],
                           value: A
                         ): List[A] = {

      list match {

        case Nil => {
          value :: Nil
        }

        case head :: tail => {
          head :: append(
            tail,
            value
          )
        }
      }
    }

    def reverse[A](
                    list: List[A]
                  ): List[A] = {

      list match {

        case Nil => {
          Nil
        }

        case head :: tail => {
          append(
            reverse(
              tail
            ),
            head
          )
        }
      }
    }
  }
}