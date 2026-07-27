package P09

class P07 {

  def pack[A](list: List[A]): List[List[A]] = {
    val n = length(list)
    if (n <= 1) {
      if (n == 0) Nil else List(list)
    } else {
      val half = n / 2
      val (left, right) = splitAt(list, half)
      merge(pack(left), pack(right))
    }
  }

  private def merge[A](leftGroups: List[List[A]], rightGroups: List[List[A]]): List[List[A]] = {
    (lastOf(leftGroups), rightGroups) match {
      case (Some(lastGroup), firstGroup :: restGroups)
          if representativeOf(lastGroup) == representativeOf(firstGroup) =>
        appendGroups(initOf(leftGroups), append(lastGroup, firstGroup) :: restGroups)
      case _ =>
        appendGroups(leftGroups, rightGroups)
    }
  }

  private def representativeOf[A](group: List[A]): A = {
    group match {
      case h :: _ => h
      case Nil    => throw new NoSuchElementException("pack should never produce an empty group")
    }
  }

  private def lastOf[A](list: List[A]): Option[A] = {
    list match {
      case Nil      => None
      case x :: Nil => Some(x)
      case _ :: t   => lastOf(t)
    }
  }

  private def initOf[A](list: List[A]): List[A] = {
    list match {
      case Nil      => Nil
      case _ :: Nil => Nil
      case h :: t   => h :: initOf(t)
    }
  }

  private def append[A](a: List[A], b: List[A]): List[A] = {
    a match {
      case Nil       => b
      case h :: rest => h :: append(rest, b)
    }
  }

  private def appendGroups[A](a: List[List[A]], b: List[List[A]]): List[List[A]] = {
    a match {
      case Nil       => b
      case h :: rest => h :: appendGroups(rest, b)
    }
  }

  private def length[A](list: List[A]): Int = {
    @annotation.tailrec
    def loop(l: List[A], acc: Int): Int = {
      l match {
        case Nil    => acc
        case _ :: t => loop(t, acc + 1)
      }
    }
    loop(list, 0)
  }

  private def splitAt[A](list: List[A], n: Int): (List[A], List[A]) = {
    @annotation.tailrec
    def loop(remaining: List[A], count: Int, accLeft: List[A]): (List[A], List[A]) = {
      if (count == 0) {
        (reverseList(accLeft), remaining)
      } else {
        remaining match {
          case Nil    => (reverseList(accLeft), Nil)
          case h :: t => loop(t, count - 1, h :: accLeft)
        }
      }
    }
    loop(list, n, Nil)
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
