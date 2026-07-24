package P09

import scala.reflect.ClassTag

class P04 {

  def pack[A](list: List[A])(implicit ct: ClassTag[A]): List[List[A]] = {
    val n = length(list)
    val source = new Array[A](n)
    fillArray(list, source, 0)

    var groups: List[List[A]] = Nil
    var i = 0
    while (i < n) {
      var j = i
      while (j < n && source(j) == source(i)) {
        j += 1
      }
      val groupSize = j - i
      val group = new Array[A](groupSize)
      var k = 0
      while (k < groupSize) {
        group(k) = source(i + k)
        k += 1
      }
      groups = arrayToList(group, groupSize) :: groups
      i = j
    }

    reverseOuter(groups)
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

  private def arrayToList[A](arr: Array[A], size: Int): List[A] = {
    def loop(idx: Int, acc: List[A]): List[A] = {
      if (idx < 0) acc else loop(idx - 1, arr(idx) :: acc)
    }
    loop(size - 1, Nil)
  }

  private def reverseOuter[A](list: List[List[A]]): List[List[A]] = {
    @annotation.tailrec
    def loop(l: List[List[A]], acc: List[List[A]]): List[List[A]] = {
      l match {
        case Nil    => acc
        case h :: t => loop(t, h :: acc)
      }
    }
    loop(list, Nil)
  }
}
