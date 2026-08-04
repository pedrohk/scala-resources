package P11

class P02 {

  def encodeModified[A](list: List[A]): List[Any] = {
    go(list, None, 0, Nil)
  }

  @annotation.tailrec
  private def go[A](remaining: List[A], current: Option[A], count: Int, acc: List[Any]): List[Any] = {
    remaining match {
      case Nil =>
        current match {
          case None        => reverseList(acc)
          case Some(value) => reverseList(toModified(count, value) :: acc)
        }
      case h :: t =>
        current match {
          case Some(value) if value == h =>
            go(t, current, count + 1, acc)
          case None =>
            go(t, Some(h), 1, acc)
          case Some(value) =>
            go(t, Some(h), 1, toModified(count, value) :: acc)
        }
    }
  }

  private def toModified[A](count: Int, value: A): Any = {
    if (count == 1) value else (count, value)
  }

  @annotation.tailrec
  private def reverseList[A](list: List[A], acc: List[A] = Nil): List[A] = {
    list match {
      case Nil    => acc
      case h :: t => reverseList(t, h :: acc)
    }
  }
}
