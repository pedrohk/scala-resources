package P12

import scala.reflect.ClassTag

class P04 {

  def decode[A](list: List[(Int, A)])(implicit ct: ClassTag[A]): List[A] = {
    val total = sumCounts(list)
    val arr = new Array[A](total)
    fill(list, arr, 0)
    arrayToList(arr, total)
  }

  private def sumCounts[A](list: List[(Int, A)]): Int = {
    list match {
      case Nil               => 0
      case (count, _) :: t   => count + sumCounts(t)
    }
  }

  private def fill[A](list: List[(Int, A)], arr: Array[A], idx: Int): Int = {
    list match {
      case Nil =>
        idx
      case (count, value) :: t =>
        val nextIdx = fillN(arr, idx, count, value)
        fill(t, arr, nextIdx)
    }
  }

  private def fillN[A](arr: Array[A], idx: Int, count: Int, value: A): Int = {
    if (count <= 0) {
      idx
    } else {
      arr(idx) = value
      fillN(arr, idx + 1, count - 1, value)
    }
  }

  private def arrayToList[A](arr: Array[A], size: Int): List[A] = {
    def loop(i: Int, acc: List[A]): List[A] = {
      if (i < 0) acc else loop(i - 1, arr(i) :: acc)
    }
    loop(size - 1, Nil)
  }
}
