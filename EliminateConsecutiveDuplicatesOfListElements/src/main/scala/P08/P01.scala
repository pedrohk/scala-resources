package P08

class P01 {

  def compress[A](list: List[A]): List[A] = {
    list match {
      case Nil =>
        Nil
      case x :: Nil =>
        x :: Nil
      case x :: y :: rest =>
        if (x == y) compress(y :: rest)
        else x :: compress(y :: rest)
    }
  }
}
