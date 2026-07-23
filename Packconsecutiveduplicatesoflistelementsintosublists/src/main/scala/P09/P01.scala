package P09

class P01 {

  def pack[A](list: List[A]): List[List[A]] = {
    list match {
      case Nil =>
        Nil
      case h :: t =>
        val (same, rest) = spanEqual(h, t)
        (h :: same) :: pack(rest)
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
}
