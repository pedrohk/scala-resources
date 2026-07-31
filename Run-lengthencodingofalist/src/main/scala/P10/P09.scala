package P10

import scala.reflect.ClassTag

class P09 {

  def encode[A](list: List[A])(implicit ct: ClassTag[A]): List[(Int, A)] = {
    var capacity = 16
    var values = new Array[A](capacity)
    var counts = new Array[Int](capacity)
    var size = 0

    def ensureCapacity(): Unit = {
      if (size >= capacity) {
        val newCapacity = capacity * 2
        val newValues = new Array[A](newCapacity)
        val newCounts = new Array[Int](newCapacity)
        var i = 0
        while (i < size) {
          newValues(i) = values(i)
          newCounts(i) = counts(i)
          i += 1
        }
        values = newValues
        counts = newCounts
        capacity = newCapacity
      }
    }

    def pushNew(value: A): Unit = {
      ensureCapacity()
      values(size) = value
      counts(size) = 1
      size += 1
    }

    def incrementLast(): Unit = {
      counts(size - 1) += 1
    }

    var remaining = list
    while (remaining.nonEmpty) {
      val value = remaining.head
      if (size == 0 || values(size - 1) != value) {
        pushNew(value)
      } else {
        incrementLast()
      }
      remaining = remaining.tail
    }

    def toListRec(i: Int, acc: List[(Int, A)]): List[(Int, A)] = {
      if (i < 0) acc else toListRec(i - 1, (counts(i), values(i)) :: acc)
    }

    toListRec(size - 1, Nil)
  }
}
