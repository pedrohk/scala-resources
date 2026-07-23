package P09

class P02 {

  def pack[A](list: List[A]): List[List[A]] = {
    go(list, Nil, Nil)
  }

  @annotation.tailrec
  private def go[A](remaining: List[A], currentGroup: List[A], acc: List[List[A]]): List[List[A]] = {
    remaining match {
      case Nil =>
        if (currentGroup.isEmpty) {
          reverseOuter(acc)
        } else {
          reverseOuter(reverseInner(currentGroup) :: acc)
        }
      case h :: t =>
        currentGroup match {
          case Nil =>
            go(t, h :: Nil, acc)
          case prev :: _ if prev == h =>
            go(t, h :: currentGroup, acc)
          case _ =>
            go(t, h :: Nil, reverseInner(currentGroup) :: acc)
        }
    }
  }

  @annotation.tailrec
  private def reverseInner[A](list: List[A], acc: List[A] = Nil): List[A] = {
    list match {
      case Nil    => acc
      case h :: t => reverseInner(t, h :: acc)
    }
  }

  @annotation.tailrec
  private def reverseOuter[A](groups: List[List[A]], acc: List[List[A]] = Nil): List[List[A]] = {
    groups match {
      case Nil    => acc
      case h :: t => reverseOuter(t, h :: acc)
    }
  }
}
