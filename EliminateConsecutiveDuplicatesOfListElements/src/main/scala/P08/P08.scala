package P08

class P08 {

  def compress[A](list: List[A]): List[A] = {
    extractHeads(pack(list))
  }

  private def pack[A](list: List[A]): List[List[A]] = {
    list match {
      case Nil =>
        Nil
      case h :: t =>
        val (sameAsHead, rest) = spanEqual(h, t)
        (h :: sameAsHead) :: pack(rest)
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

  private def extractHeads[A](groups: List[List[A]]): List[A] = {
    groups match {
      case Nil =>
        Nil
      case group :: restGroups =>
        group match {
          case Nil          => extractHeads(restGroups)
          case first :: _   => first :: extractHeads(restGroups)
        }
    }
  }
}
