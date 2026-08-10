package P12

class P02 {

  def decode[A](list: List[(Int, A)]): List[A] = {
    go(list, Nil)
  }

  @annotation.tailrec
  private def go[A](remaining: List[(Int, A)], acc: List[A]): List[A] = {
    remaining match {
      case Nil =>
        reverseList(acc)
      case (count, value) :: t =>
        go(t, prependN(count, value, acc))
    }
  }

  @annotation.tailrec
  private def prependN[A](count: Int, value: A, acc: List[A]): List[A] = {
    if (count <= 0) acc
    else prependN(count - 1, value, value :: acc)
  }

  @annotation.tailrec
  private def reverseList[A](list: List[A], acc: List[A] = Nil): List[A] = {
    list match {
      case Nil    => acc
      case h :: t => reverseList(t, h :: acc)
    }
  }
}
