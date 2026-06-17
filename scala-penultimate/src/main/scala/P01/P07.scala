package P01 {

  object P07 {

    def penultimate[A](
                        list: List[A]
                      ): A = {

      def slide(
                 previous: List[A],
                 current: List[A]
               ): A = {

        current match {

          case Nil => {
            throw new NoSuchElementException(
              "List too short"
            )
          }

          case _ :: Nil => {

            previous match {

              case value :: _ => {
                value
              }

              case _ => {
                throw new NoSuchElementException(
                  "List too short"
                )
              }
            }
          }

          case _ :: tail => {

            previous match {

              case _ :: previousTail => {

                slide(
                  previousTail,
                  tail
                )
              }

              case _ => {

                throw new NoSuchElementException(
                  "List too short"
                )
              }
            }
          }
        }
      }

      list match {

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

        case _ => {

          slide(
            list,
            list.tail
          )
        }
      }
    }
  }
}