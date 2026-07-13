package P07

class P02 {

  def flatten(list: List[Any]): List[Any] = {
    go(list, Nil)
  }

  private def go(remaining: List[Any], acc: List[Any]): List[Any] = {
    remaining match {
      case Nil =>
        reverse(acc)
      case (head: List[Any]) :: tail =>
        val flattenedHead = go(head, Nil)
        go(tail, prepend(flattenedHead, acc))
      case head :: tail =>
        go(tail, head :: acc)
    }
  }

  @annotation.tailrec
  private def prepend(elems: List[Any], accIn: List[Any]): List[Any] = {
    elems match {
      case Nil          => accIn
      case x :: xsTail  => prepend(xsTail, x :: accIn)
    }
  }

  @annotation.tailrec
  private def reverse(l: List[Any], acc: List[Any] = Nil): List[Any] = {
    l match {
      case Nil       => acc
      case h :: rest => reverse(rest, h :: acc)
    }
  }
}
