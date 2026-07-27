package P09

class P08 {

  def pack[A](list: List[A]): List[List[A]] = {
    list match {
      case Nil =>
        Nil
      case h :: _ =>
        val count = countLeadingEqual(h, list)
        val (group, rest) = takeAndDrop(list, count)
        group :: pack(rest)
    }
  }

  private def countLeadingEqual[A](value: A, list: List[A]): Int = {
    list match {
      case h :: t if h == value => 1 + countLeadingEqual(value, t)
      case _                    => 0
    }
  }

  private def takeAndDrop[A](list: List[A], n: Int): (List[A], List[A]) = {
    if (n == 0) {
      (Nil, list)
    } else {
      list match {
        case h :: t =>
          val (taken, rest) = takeAndDrop(t, n - 1)
          (h :: taken, rest)
        case Nil =>
          (Nil, Nil)
      }
    }
  }
}
