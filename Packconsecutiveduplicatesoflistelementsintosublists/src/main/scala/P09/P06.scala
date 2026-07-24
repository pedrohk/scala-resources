package P09

class P06 {

  def pack[A](list: List[A]): List[List[A]] = {
    packFrom(list)
  }

  private def packFrom[A](list: List[A]): List[List[A]] = {
    list match {
      case Nil    => Nil
      case h :: t => continueGroup(h, h :: Nil, t)
    }
  }

  private def continueGroup[A](value: A, accGroupReversed: List[A], remaining: List[A]): List[List[A]] = {
    remaining match {
      case h :: t if h == value =>
        continueGroup(value, h :: accGroupReversed, t)
      case other =>
        reverseList(accGroupReversed) :: packFrom(other)
    }
  }

  private def reverseList[A](list: List[A]): List[A] = {
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
