package P08

class P07 {

  def compress[A](list: List[A]): List[A] = {
    val n = length(list)
    if (n <= 1) {
      list
    } else {
      val half = n / 2
      val (left, right) = splitAt(list, half)
      merge(compress(left), compress(right))
    }
  }

  private def merge[A](left: List[A], right: List[A]): List[A] = {
    (lastOf(left), right) match {
      case (Some(lastLeft), firstRight :: restRight) if lastLeft == firstRight =>
        append(left, restRight)
      case _ =>
        append(left, right)
    }
  }

  private def lastOf[A](list: List[A]): Option[A] = {
    list match {
      case Nil     => None
      case x :: Nil => Some(x)
      case _ :: t  => lastOf(t)
    }
  }

  private def append[A](a: List[A], b: List[A]): List[A] = {
    a match {
      case Nil       => b
      case h :: rest => h :: append(rest, b)
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
        (reverse(accLeft), remaining)
      } else {
        remaining match {
          case Nil    => (reverse(accLeft), Nil)
          case h :: t => loop(t, count - 1, h :: accLeft)
        }
      }
    }
    loop(list, n, Nil)
  }

  private def reverse[A](list: List[A]): List[A] = {
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
