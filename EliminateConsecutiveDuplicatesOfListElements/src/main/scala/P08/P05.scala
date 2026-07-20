package P08


class P05 {

  def compress[A](list: List[A]): List[A] = {
    go(list)(identity)
  }

  private def go[A](remaining: List[A])(k: List[A] => List[A]): List[A] = {
    remaining match {
      case Nil =>
        k(Nil)
      case x :: Nil =>
        k(x :: Nil)
      case x :: y :: rest =>
        if (x == y) {
          go(y :: rest)(k)
        } else {
          go(y :: rest) { tailResult => k(x :: tailResult) }
        }
    }
  }
}
