package P12

class P06 {

  def decode[A](list: List[(Int, A)]): List[A] = {
    decodeFrom(list)
  }

  private def decodeFrom[A](list: List[(Int, A)]): List[A] = {
    list match {
      case Nil                 => Nil
      case (count, value) :: t => expandCount(count, value, t)
    }
  }

  private def expandCount[A](remainingCount: Int, value: A, rest: List[(Int, A)]): List[A] = {
    if (remainingCount <= 0) {
      decodeFrom(rest)
    } else {
      value :: expandCount(remainingCount - 1, value, rest)
    }
  }
}
