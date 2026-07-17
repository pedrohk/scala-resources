package P08

class P02 {

  def compress[A](list: List[A]): List[A] = {
    go(list, None, Nil)
  }

  @annotation.tailrec
  private def go[A](remaining: List[A], last: Option[A], acc: List[A]): List[A] = {
    remaining match {
      case Nil =>
        reverse(acc)
      case h :: t =>
        last match {
          case Some(prev) if prev == h =>
            go(t, last, acc)
          case _ =>
            go(t, Some(h), h :: acc)
        }
    }
  }

  @annotation.tailrec
  private def reverse[A](list: List[A], acc: List[A] = Nil): List[A] = {
    list match {
      case Nil    => acc
      case h :: t => reverse(t, h :: acc)
    }
  }
}
