package P04 {

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

    private def count[A](
                          node: Node[A]
                        ): Int = {

      node match {

        case End => {
          0
        }

        case Link(
          _,
          next
        ) => {

          1 + count(
            next
          )
        }
      }
    }

    def length[A](
                   list: List[A]
                 ): Int = {

      count(
        build(
          list
        )
      )
    }
  }
}