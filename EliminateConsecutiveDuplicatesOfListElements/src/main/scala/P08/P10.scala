package P08

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

  def compress[A](list: List[A]): List[A] = {
    val reversedResult = myFoldLeft[A, List[A]](list, Nil) { (acc, elem) =>
      acc match {
        case head :: _ if head == elem => acc
        case _                         => elem :: acc
      }
    }
    reverse(reversedResult)
  }

  private def reverse[A](list: List[A]): List[A] = {
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
