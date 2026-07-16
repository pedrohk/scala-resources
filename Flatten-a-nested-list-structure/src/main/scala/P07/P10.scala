package P07


class P10 {

  private def myFoldRight[A, B](list: List[A], seed: B)(combine: (A, B) => B): B = {
    list match {
      case Nil    => seed
      case h :: t => combine(h, myFoldRight(t, seed)(combine))
    }
  }

  def flatten(list: List[Any]): List[Any] = {
    myFoldRight[Any, List[Any]](list, Nil) { (elem, acc) =>
      elem match {
        case nested: List[Any] =>
          myFoldRight[Any, List[Any]](flatten(nested), acc)((e, a) => e :: a)
        case leaf =>
          leaf :: acc
      }
    }
  }
}
