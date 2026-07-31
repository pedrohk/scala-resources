package P10

class P08 {

  def encode[A](list: List[A]): List[(Int, A)] = {
    list match {
      case Nil =>
        Nil
      case h :: _ =>
        val count = countLeadingEqual(h, list)
        val rest = dropN(list, count)
        (count, h) :: encode(rest)
    }
  }

  private def countLeadingEqual[A](value: A, list: List[A]): Int = {
    list match {
      case h :: t if h == value => 1 + countLeadingEqual(value, t)
      case _                    => 0
    }
  }

  private def dropN[A](list: List[A], n: Int): List[A] = {
    if (n == 0) {
      list
    } else {
      list match {
        case _ :: t => dropN(t, n - 1)
        case Nil    => Nil
      }
    }
  }
}
