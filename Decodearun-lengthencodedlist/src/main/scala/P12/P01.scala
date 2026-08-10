package P12

class P01 {

  def decode[A](list: List[(Int, A)]): List[A] = {
    list match {
      case Nil =>
        Nil
      case (count, value) :: t =>
        append(replicate(count, value), decode(t))
    }
  }

  private def replicate[A](count: Int, value: A): List[A] = {
    if (count <= 0) Nil
    else value :: replicate(count - 1, value)
  }

  private def append[A](a: List[A], b: List[A]): List[A] = {
    a match {
      case Nil       => b
      case h :: rest => h :: append(rest, b)
    }
  }
}
