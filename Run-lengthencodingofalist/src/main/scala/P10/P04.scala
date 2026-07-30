package P10

import scala.reflect.ClassTag

class P04 {

  def encode[A](list: List[A])(implicit ct: ClassTag[A]): List[(Int, A)] = {
    val n = length(list)
    val source = new Array[A](n)
    fillArray(list, source, 0)

    var result: List[(Int, A)] = Nil
    var i = 0
    while (i < n) {
      var j = i
      while (j < n && source(j) == source(i)) {
        j += 1
      }
      result = (j - i, source(i)) :: result
      i = j
    }

    reverseList(result)
  }

  private def length[A](list: List[A]): Int = {
    @annotation.tailrec
    def loop(l: List[A], acc: Int): Int = {
      l match {
        case Nil    => acc
        case _ :: t => loop(t, acc + 1)
      }
    }
    loop(list, 0)
  }

  private def fillArray[A](list: List[A], arr: Array[A], idx: Int): Unit = {
    list match {
      case Nil    => ()
      case h :: t =>
        arr(idx) = h
        fillArray(t, arr, idx + 1)
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
