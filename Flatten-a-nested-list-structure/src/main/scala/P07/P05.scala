package P07


class P05 {

  def flatten(list: List[Any]): List[Any] = {
    go(list)(identity)
  }

  private def go(remaining: List[Any])(k: List[Any] => List[Any]): List[Any] = {
    remaining match {
      case Nil =>
        k(Nil)
      case (head: List[Any]) :: tail =>
        go(head) { flattenedHead =>
          go(tail) { flattenedTail =>
            k(concat(flattenedHead, flattenedTail))
          }
        }
      case head :: tail =>
        go(tail) { flattenedTail =>
          k(head :: flattenedTail)
        }
    }
  }

  private def concat(a: List[Any], b: List[Any]): List[Any] = {
    a match {
      case Nil       => b
      case h :: rest => h :: concat(rest, b)
    }
  }
}
