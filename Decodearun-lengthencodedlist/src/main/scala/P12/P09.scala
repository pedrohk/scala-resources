package P12

import scala.reflect.ClassTag

class P09 {

  def decode[A](list: List[(Int, A)])(implicit ct: ClassTag[A]): List[A] = {
    var capacity = 16
    var arr = new Array[A](capacity)
    var size = 0

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
      val (count, value) = remaining.head
      var i = 0
      while (i < count) {
        push(value)
        i += 1
      }
      remaining = remaining.tail
    }

    def arrayToList(a: Array[A], n: Int): List[A] = {
      def loop(i: Int, acc: List[A]): List[A] = {
        if (i < 0) acc else loop(i - 1, a(i) :: acc)
      }
      loop(n - 1, Nil)
    }

    arrayToList(arr, size)
  }
}
