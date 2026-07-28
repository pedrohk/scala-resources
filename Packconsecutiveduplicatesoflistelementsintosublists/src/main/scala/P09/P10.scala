package P09

class P10 {

  private def myFoldLeft[A, B](list: List[A], seed: B)(combine: (B, A) => B): B = {
    @annotation.tailrec
    def loop(remaining: List[A], acc: B): B = {
      remaining match {
        case Nil    => acc
        case h :: t => loop(t, combine(acc, h))
      }
    }
    loop(list, seed)
  }

  def pack[A](list: List[A]): List[List[A]] = {
    val reversedGroupsOfReversedElements = myFoldLeft[A, List[List[A]]](list, Nil) { (acc, elem) =>
      acc match {
        case (group @ (head :: _)) :: restGroups if head == elem =>
          (elem :: group) :: restGroups
        case _ =>
          (elem :: Nil) :: acc
      }
    }
    reverseOuter(reversedGroupsOfReversedElements)
  }

  private def reverseOuter[A](groups: List[List[A]]): List[List[A]] = {
    @annotation.tailrec
    def loop(remaining: List[List[A]], acc: List[List[A]]): List[List[A]] = {
      remaining match {
        case Nil    => acc
        case h :: t => loop(t, reverseInner(h) :: acc)
      }
    }
    loop(groups, Nil)
  }

  private def reverseInner[A](list: List[A]): List[A] = {
    @annotation.tailrec
    def loop(l: List[A], acc: List[A]): List[A] = {
      l match {
        case Nil    => acc
        case h :: t => loop(t, h :: acc)
      }
    }
    loop(list, Nil)
  }
}
