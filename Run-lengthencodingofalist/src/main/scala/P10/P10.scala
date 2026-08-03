package P10

class P10 {

  private def myFoldLeft[A, B](list: List[A], seed: B)(combine: (B, A) => B): B = {
    @annotation.tailrec
    def loop(remaining: List[A], acc: B): B = {
      remaining match {
        case Nil    => acc
        case h :: t => loop(t, combine(acc, h))
      }
    }
    loop(list, seed)
  }

  def encode[A](list: List[A]): List[(Int, A)] = {
    val reversedResult = myFoldLeft[A, List[(Int, A)]](list, Nil) { (acc, elem) =>
      acc match {
        case (count, value) :: rest if value == elem =>
          (count + 1, value) :: rest
        case _ =>
          (1, elem) :: acc
      }
    }
    reverseList(reversedResult)
  }

  private def reverseList[A](list: List[A]): List[A] = {
    @annotation.tailrec
    def loop(l: List[A], acc: List[A]): List[A] = {
      l match {
        case Nil    => acc
        case h :: t => loop(t, h :: acc)
      }
    }
    loop(list, Nil)
  }
}
