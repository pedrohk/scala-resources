package P10

class P01 {

  def encode[A](list: List[A]): List[(Int, A)] = {
    list match {
      case Nil =>
        Nil
      case h :: t =>
        val (same, rest) = spanEqual(h, t)
        (1 + length(same), h) :: encode(rest)
    }
  }

  private def spanEqual[A](value: A, list: List[A]): (List[A], List[A]) = {
    list match {
      case h :: t if h == value =>
        val (same, rest) = spanEqual(value, t)
        (h :: same, rest)
      case other =>
        (Nil, other)
    }
  }

  private def length[A](list: List[A]): Int = {
    list match {
      case Nil    => 0
      case _ :: t => 1 + length(t)
    }
  }
}
