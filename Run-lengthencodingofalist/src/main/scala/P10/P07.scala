package P10

class P07 {

  def encode[A](list: List[A]): List[(Int, A)] = {
    val n = length(list)
    if (n <= 1) {
      if (n == 0) Nil else List((1, list.head))
    } else {
      val half = n / 2
      val (left, right) = splitAt(list, half)
      merge(encode(left), encode(right))
    }
  }

  private def merge[A](leftEncoded: List[(Int, A)], rightEncoded: List[(Int, A)]): List[(Int, A)] = {
    (lastOf(leftEncoded), rightEncoded) match {
      case (Some((lastCount, lastValue)), (firstCount, firstValue) :: restEncoded)
          if lastValue == firstValue =>
        appendTuples(initOf(leftEncoded), (lastCount + firstCount, lastValue) :: restEncoded)
      case _ =>
        appendTuples(leftEncoded, rightEncoded)
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

  private def appendTuples[A](a: List[(Int, A)], b: List[(Int, A)]): List[(Int, A)] = {
    a match {
      case Nil       => b
      case h :: rest => h :: appendTuples(rest, b)
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
