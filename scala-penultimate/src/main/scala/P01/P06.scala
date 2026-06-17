package P01 {

  object P06 {

    def penultimate[A](
                        list: List[A]
                      ): A = {

      var previous: Option[A] =
        None

      var current =
        list

      current match {

        case Nil => {
          throw new NoSuchElementException(
            "List too short"
          )
        }

        case _ :: Nil => {
          throw new NoSuchElementException(
            "List too short"
          )
        }

        case _ => {}
      }

      while (
        current match {
          case _ :: _ :: _ => {
            true
          }

          case _ => {
            false
          }
        }
      ) {

        current match {

          case head :: tail => {

            previous =
              Some(head)

            current =
              tail
          }

          case _ => {}
        }
      }

      previous match {

        case Some(value) => {
          value
        }

        case None => {
          throw new NoSuchElementException(
            "List too short"
          )
        }
      }
    }
  }
}