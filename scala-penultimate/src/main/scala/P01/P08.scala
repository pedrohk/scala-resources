package P01 {

  object P08 {

    def penultimate[A](
                        list: List[A]
                      ): A = {

      search(
        build(
          list
        )
      )
    }

    private def build[A](
                          list: List[A]
                        ): Node[A] = {

      list match {

        case Nil => {
          End
        }

        case head :: tail => {

          Link(
            head,
            build(
              tail
            )
          )
        }
      }
    }

    private def search[A](
                           node: Node[A]
                         ): A = {

      node match {

        case Link(
          value,
          Link(
            _,
            End
          )
        ) => {

          value
        }

        case Link(
          _,
          next
        ) => {

          search(
            next
          )
        }

        case _ => {

          throw new NoSuchElementException(
            "List too short"
          )
        }
      }
    }

    sealed trait Node[+A]

    final case class Link[A](
                              value: A,
                              next: Node[A]
                            ) extends Node[A]

    case object End
      extends Node[Nothing]
  }
}