package P06 {

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

    private def reverseNode[A](
                                node: Node[A],
                                result: List[A]
                              ): List[A] = {

      node match {

        case End => {
          result
        }

        case Link(
          value,
          next
        ) => {

          reverseNode(
            next,
            value :: result
          )
        }
      }
    }

    private def same[A](
                         left: List[A],
                         right: List[A]
                       ): Boolean = {

      (left, right) match {

        case (Nil, Nil) => {
          true
        }

        case (
          leftHead :: leftTail,
          rightHead :: rightTail
        ) => {

          if (
            leftHead == rightHead
          ) {

            same(
              leftTail,
              rightTail
            )

          } else {
            false
          }
        }

        case _ => {
          false
        }
      }
    }

    def isPalindrome[A](
                         list: List[A]
                       ): Boolean = {

      same(
        list,
        reverseNode(
          build(
            list
          ),
          Nil
        )
      )
    }
  }
}