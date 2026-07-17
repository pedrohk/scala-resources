package P08

import scala.reflect.ClassTag

class P03 {

  def compress[A](list: List[A])(implicit ct: ClassTag[A]): List[A] = {
    val n = length(list)
    val source = new Array[A](n)
    fillArray(list, source, 0)

    var resultCapacity = if (n > 0) n else 1
    var resultArr = new Array[A](resultCapacity)
    var resultSize = 0

    def addResult(value: A): Unit = {
      if (resultSize >= resultCapacity) {
        val newCapacity = resultCapacity * 2
        val newArr = new Array[A](newCapacity)
        var i = 0
        while (i < resultSize) {
          newArr(i) = resultArr(i)
          i += 1
        }
        resultArr = newArr
        resultCapacity = newCapacity
      }
      resultArr(resultSize) = value
      resultSize += 1
    }

    var i = 0
    while (i < n) {
      if (i == 0 || source(i) != source(i - 1)) {
        addResult(source(i))
      }
      i += 1
    }

    arrayToList(resultArr, resultSize)
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
    def loop(i: Int, acc: List[A]): List[A] = {
      if (i < 0) acc else loop(i - 1, arr(i) :: acc)
    }
    loop(size - 1, Nil)
  }
}
