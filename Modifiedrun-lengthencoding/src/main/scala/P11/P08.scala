package P11

class P08 {

  def encodeModified[A](list: List[A]): List[Any] = {
    list match {
      case Nil =>
        Nil
      case h :: _ =>
        val count = countLeadingEqual(h, list)
        val rest = dropN(list, count)
        toModified(count, h) :: encodeModified(rest)
    }
  }

  private def toModified[A](count: Int, value: A): Any = {
    if (count == 1) value else (count, value)
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
