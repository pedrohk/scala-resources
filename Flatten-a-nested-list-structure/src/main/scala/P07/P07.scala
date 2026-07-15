package P07

class P07 {

  def flatten(list: List[Any]): List[Any] = {
    val n = length(list)
    if (n == 0) {
      Nil
    } else if (n == 1) {
      list.head match {
        case nested: List[Any] => flatten(nested)
        case leaf               => leaf :: Nil
      }
    } else {
      val half = n / 2
      val (left, right) = splitAt(list, half)
      concat(flatten(left), flatten(right))
    }
  }

  private def length(list: List[Any]): Int = {
    @annotation.tailrec
    def loop(l: List[Any], acc: Int): Int = {
      l match {
        case Nil    => acc
        case _ :: t => loop(t, acc + 1)
      }
    }
    loop(list, 0)
  }

  private def splitAt(list: List[Any], n: Int): (List[Any], List[Any]) = {
    @annotation.tailrec
    def loop(remaining: List[Any], count: Int, accLeft: List[Any]): (List[Any], List[Any]) = {
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

  private def reverse(list: List[Any]): List[Any] = {
    @annotation.tailrec
    def loop(l: List[Any], acc: List[Any]): List[Any] = {
      l match {
        case Nil    => acc
        case h :: t => loop(t, h :: acc)
      }
    }
    loop(list, Nil)
  }

  private def concat(a: List[Any], b: List[Any]): List[Any] = {
    a match {
      case Nil       => b
      case h :: rest => h :: concat(rest, b)
    }
  }
}
