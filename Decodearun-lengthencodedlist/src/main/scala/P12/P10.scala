package P12

class P10 {

  private def myFoldRight[A, B](list: List[A], seed: B)(combine: (A, B) => B): B = {
    list match {
      case Nil    => seed
      case h :: t => combine(h, myFoldRight(t, seed)(combine))
    }
  }

  def decode[A](list: List[(Int, A)]): List[A] = {
    myFoldRight[(Int, A), List[A]](list, Nil) { (pair, acc) =>
      val (count, value) = pair
      prependN(count, value, acc)
    }
  }

  private def prependN[A](count: Int, value: A, tail: List[A]): List[A] = {
    if (count <= 0) tail
    else value :: prependN(count - 1, value, tail)
  }
}
