package P12

class P08 {

  def decode[A](list: List[(Int, A)]): List[A] = {
    list match {
      case Nil =>
        Nil
      case (count, value) :: t =>
        append(replicateFast(count, value), decode(t))
    }
  }

  private def replicateFast[A](count: Int, value: A): List[A] = {
    if (count <= 0) {
      Nil
    } else if (count == 1) {
      value :: Nil
    } else {
      val half = count / 2
      val halfList = replicateFast(half, value)
      val extra = if (count % 2 == 0) Nil else value :: Nil
      append(append(halfList, halfList), extra)
    }
  }

  private def append[A](a: List[A], b: List[A]): List[A] = {
    a match {
      case Nil       => b
      case h :: rest => h :: append(rest, b)
    }
  }
}
