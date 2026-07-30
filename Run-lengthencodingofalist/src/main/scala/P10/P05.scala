package P10

class P05 {

  def encode[A](list: List[A]): List[(Int, A)] = {
    go(list)(identity)
  }

  private def go[A](remaining: List[A])(k: List[(Int, A)] => List[(Int, A)]): List[(Int, A)] = {
    remaining match {
      case Nil =>
        k(Nil)
      case h :: t =>
        countEqual(h, t) { (count, rest) =>
          go(rest) { restEncoded =>
            k((count + 1, h) :: restEncoded)
          }
        }
    }
  }

  private def countEqual[A](value: A, list: List[A])(k: (Int, List[A]) => List[(Int, A)]): List[(Int, A)] = {
    list match {
      case h :: t if h == value =>
        countEqual(value, t) { (count, rest) =>
          k(count + 1, rest)
        }
      case other =>
        k(0, other)
    }
  }
}
