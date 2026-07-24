package P09

class P05 {

  def pack[A](list: List[A]): List[List[A]] = {
    go(list)(identity)
  }

  private def go[A](remaining: List[A])(k: List[List[A]] => List[List[A]]): List[List[A]] = {
    remaining match {
      case Nil =>
        k(Nil)
      case h :: t =>
        spanEqual(h, t) { (same, rest) =>
          go(rest) { restGroups =>
            k((h :: same) :: restGroups)
          }
        }
    }
  }

  private def spanEqual[A](value: A, list: List[A])(k: (List[A], List[A]) => List[List[A]]): List[List[A]] = {
    list match {
      case h :: t if h == value =>
        spanEqual(value, t) { (same, rest) =>
          k(h :: same, rest)
        }
      case other =>
        k(Nil, other)
    }
  }
}
