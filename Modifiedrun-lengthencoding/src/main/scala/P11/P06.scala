package P11

class P06 {

  def encodeModified[A](list: List[A]): List[Any] = {
    encodeFrom(list)
  }

  private def encodeFrom[A](list: List[A]): List[Any] = {
    list match {
      case Nil    => Nil
      case h :: t => continueRun(h, 1, t)
    }
  }

  private def continueRun[A](value: A, count: Int, remaining: List[A]): List[Any] = {
    remaining match {
      case h :: t if h == value =>
        continueRun(value, count + 1, t)
      case other =>
        toModified(count, value) :: encodeFrom(other)
    }
  }

  private def toModified[A](count: Int, value: A): Any = {
    if (count == 1) value else (count, value)
  }
}
