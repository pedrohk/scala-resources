package P10

class P02 {

  def encode[A](list: List[A]): List[(Int, A)] = {
    go(list, None, 0, Nil)
  }

  @annotation.tailrec
  private def go[A](remaining: List[A], current: Option[A], count: Int, acc: List[(Int, A)]): List[(Int, A)] = {
    remaining match {
      case Nil =>
        current match {
          case None        => reverseList(acc)
          case Some(value) => reverseList((count, value) :: acc)
        }
      case h :: t =>
        current match {
          case Some(value) if value == h =>
            go(t, current, count + 1, acc)
          case None =>
            go(t, Some(h), 1, acc)
          case Some(value) =>
            go(t, Some(h), 1, (count, value) :: acc)
        }
    }
  }

  @annotation.tailrec
  private def reverseList[A](list: List[A], acc: List[A] = Nil): List[A] = {
    list match {
      case Nil    => acc
      case h :: t => reverseList(t, h :: acc)
    }
  }
}
