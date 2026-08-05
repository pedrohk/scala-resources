package P11

class P05 {

  def encodeModified[A](list: List[A]): List[Any] = {
    go(list)(identity)
  }

  private def go[A](remaining: List[A])(k: List[Any] => List[Any]): List[Any] = {
    remaining match {
      case Nil =>
        k(Nil)
      case h :: t =>
        countEqual(h, t) { (count, rest) =>
          go(rest) { restEncoded =>
            k(toModified(count + 1, h) :: restEncoded)
          }
        }
    }
  }

  private def countEqual[A](value: A, list: List[A])(k: (Int, List[A]) => List[Any]): List[Any] = {
    list match {
      case h :: t if h == value =>
        countEqual(value, t) { (count, rest) =>
          k(count + 1, rest)
        }
      case other =>
        k(0, other)
    }
  }

  private def toModified[A](count: Int, value: A): Any = {
    if (count == 1) value else (count, value)
  }
}
