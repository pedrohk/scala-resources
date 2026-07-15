package P07

class P08 {

  def flatten(list: List[Any]): List[Any] = {
    go(list)(Nil)
  }

  private def go(remaining: List[Any]): List[Any] => List[Any] = {
    remaining match {
      case Nil =>
        (rest: List[Any]) => rest
      case (head: List[Any]) :: tail =>
        val fHead = go(head)
        val fTail = go(tail)
        (rest: List[Any]) => fHead(fTail(rest))
      case head :: tail =>
        val fTail = go(tail)
        (rest: List[Any]) => head :: fTail(rest)
    }
  }
}
