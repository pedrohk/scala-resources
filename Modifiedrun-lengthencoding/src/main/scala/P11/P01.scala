package P11

class P01 {

  def encodeModified[A](list: List[A]): List[Any] = {
    list match {
      case Nil =>
        Nil
      case h :: t =>
        val (same, rest) = spanEqual(h, t)
        val count = 1 + length(same)
        toModified(count, h) :: encodeModified(rest)
    }
  }

  private def toModified[A](count: Int, value: A): Any = {
    if (count == 1) value else (count, value)
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
