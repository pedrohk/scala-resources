package P12

class P07 {

  def decode[A](list: List[(Int, A)]): List[A] = {
    val n = length(list)
    if (n <= 1) {
      if (n == 0) Nil else expand(list.head)
    } else {
      val half = n / 2
      val (left, right) = splitAt(list, half)
      append(decode(left), decode(right))
    }
  }

  private def expand[A](pair: (Int, A)): List[A] = {
    val (count, value) = pair
    replicate(count, value)
  }

  private def replicate[A](count: Int, value: A): List[A] = {
    if (count <= 0) Nil else value :: replicate(count - 1, value)
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
