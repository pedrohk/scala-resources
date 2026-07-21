package P08

import scala.reflect.ClassTag

class P09 {

  def compress[A](list: List[A])(implicit ct: ClassTag[A]): List[A] = {
    var capacity = 16
    var arr = new Array[A](capacity)
    var size = 0
    var lastValue: A = null.asInstanceOf[A]
    var hasLast = false

    def ensureCapacity(): Unit = {
      if (size >= capacity) {
        val newCapacity = capacity * 2
        val newArr = new Array[A](newCapacity)
        var i = 0
        while (i < size) {
          newArr(i) = arr(i)
          i += 1
        }
        arr = newArr
        capacity = newCapacity
      }
    }

    def push(value: A): Unit = {
      ensureCapacity()
      arr(size) = value
      size += 1
    }

    var remaining = list
    while (remaining.nonEmpty) {
      val current = remaining.head
      if (!hasLast || lastValue != current) {
        push(current)
        lastValue = current
        hasLast = true
      }
      remaining = remaining.tail
    }

    arrayToList(arr, size)
  }

  private def arrayToList[A](arr: Array[A], size: Int): List[A] = {
    def loop(i: Int, acc: List[A]): List[A] = {
      if (i < 0) acc else loop(i - 1, arr(i) :: acc)
    }
    loop(size - 1, Nil)
  }
}
