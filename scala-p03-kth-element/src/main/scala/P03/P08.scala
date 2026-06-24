package P03 {

  object P08 {

    sealed trait Node[+A]

    final case class Link[A](
                              value: A,
                              next: Node[A]
                            ) extends Node[A]

    case object End
      extends Node[Nothing]

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
                           node: Node[A],
                           target: Int,
                           current: Int
                         ): A = {

      node match {

        case End => {

          throw new IndexOutOfBoundsException(
            "Index out of range"
          )
        }

        case Link(
          value,
          next
        ) => {

          if (
            current ==
              target
          ) {

            value

          } else {

            search(
              next,
              target,
              current + 1
            )
          }
        }
      }
    }

    def nth[A](
                target: Int,
                list: List[A]
              ): A = {

      if (
        target < 0
      ) {

        throw new IndexOutOfBoundsException(
          "Negative index"
        )
      }

      search(
        build(
          list
        ),
        target,
        0
      )
    }
  }
}