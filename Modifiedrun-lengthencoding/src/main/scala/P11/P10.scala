package P11

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

  def encodeModified[A](list: List[A]): List[Any] = {
    val reversedCounts = myFoldLeft[A, List[(Int, A)]](list, Nil) { (acc, elem) =>
      acc match {
        case (count, value) :: rest if value == elem =>
          (count + 1, value) :: rest
        case _ =>
          (1, elem) :: acc
      }
    }
    val counts = reverseList(reversedCounts)
    toModifiedList(counts)
  }

  private def toModifiedList[A](counts: List[(Int, A)]): List[Any] = {
    counts match {
      case Nil =>
        Nil
      case (count, value) :: t =>
        val head: Any = if (count == 1) value else (count, value)
        head :: toModifiedList(t)
    }
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
