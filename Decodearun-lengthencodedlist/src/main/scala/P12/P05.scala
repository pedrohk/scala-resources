package P12

class P05 {

  def decode[A](list: List[(Int, A)]): List[A] = {
    go(list)(identity)
  }

  private def go[A](remaining: List[(Int, A)])(k: List[A] => List[A]): List[A] = {
    remaining match {
      case Nil =>
        k(Nil)
      case (count, value) :: t =>
        go(t) { restDecoded =>
          k(replicate(count, value, restDecoded))
        }
    }
  }

  private def replicate[A](count: Int, value: A, tail: List[A]): List[A] = {
    if (count <= 0) tail
    else value :: replicate(count - 1, value, tail)
  }
}
