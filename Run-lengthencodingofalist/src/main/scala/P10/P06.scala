package P10

class P06 {

  def encode[A](list: List[A]): List[(Int, A)] = {
    encodeFrom(list)
  }

  private def encodeFrom[A](list: List[A]): List[(Int, A)] = {
    list match {
      case Nil    => Nil
      case h :: t => continueRun(h, 1, t)
    }
  }

  private def continueRun[A](value: A, count: Int, remaining: List[A]): List[(Int, A)] = {
    remaining match {
      case h :: t if h == value =>
        continueRun(value, count + 1, t)
      case other =>
        (count, value) :: encodeFrom(other)
    }
  }
}
