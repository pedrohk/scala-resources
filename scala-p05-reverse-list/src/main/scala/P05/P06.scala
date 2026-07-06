package P05 {

  object P06 {

    def reverse[A](
                    list: List[A]
                  ): List[A] = {

      var current =
        list

      var result: List[A] =
        Nil

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

          case head :: tail => {

            result =
              head :: result

            current =
              tail
          }

          case Nil => {}
        }
      }

      result
    }
  }
}