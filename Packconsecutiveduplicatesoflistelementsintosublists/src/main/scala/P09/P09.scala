package P09

import scala.reflect.ClassTag

class P09 {

  def pack[A](list: List[A])(implicit ct: ClassTag[A]): List[List[A]] = {
    var outerCapacity = 16
    var outerArr = new Array[Array[A]](outerCapacity)
    var outerSize = 0

    var currentCapacity = 8
    var currentArr = new Array[A](currentCapacity)
    var currentSize = 0
    var hasCurrent = false
    var currentValue: A = null.asInstanceOf[A]

    def ensureOuterCapacity(): Unit = {
      if (outerSize >= outerCapacity) {
        val newCapacity = outerCapacity * 2
        val newArr = new Array[Array[A]](newCapacity)
        var i = 0
        while (i < outerSize) {
          newArr(i) = outerArr(i)
          i += 1
        }
        outerArr = newArr
        outerCapacity = newCapacity
      }
    }

    def pushOuter(group: Array[A]): Unit = {
      ensureOuterCapacity()
      outerArr(outerSize) = group
      outerSize += 1
    }

    def resetCurrent(): Unit = {
      currentCapacity = 8
      currentArr = new Array[A](currentCapacity)
      currentSize = 0
    }

    def ensureCurrentCapacity(): Unit = {
      if (currentSize >= currentCapacity) {
        val newCapacity = currentCapacity * 2
        val newArr = new Array[A](newCapacity)
        var i = 0
        while (i < currentSize) {
          newArr(i) = currentArr(i)
          i += 1
        }
        currentArr = newArr
        currentCapacity = newCapacity
      }
    }

    def pushCurrent(value: A): Unit = {
      ensureCurrentCapacity()
      currentArr(currentSize) = value
      currentSize += 1
    }

    def flushCurrent(): Unit = {
      if (hasCurrent) {
        val trimmed = new Array[A](currentSize)
        var i = 0
        while (i < currentSize) {
          trimmed(i) = currentArr(i)
          i += 1
        }
        pushOuter(trimmed)
      }
    }

    var remaining = list
    while (remaining.nonEmpty) {
      val value = remaining.head
      if (!hasCurrent) {
        resetCurrent()
        pushCurrent(value)
        currentValue = value
        hasCurrent = true
      } else if (currentValue == value) {
        pushCurrent(value)
      } else {
        flushCurrent()
        resetCurrent()
        pushCurrent(value)
        currentValue = value
      }
      remaining = remaining.tail
    }
    flushCurrent()

    def arrayToList(arr: Array[A], size: Int): List[A] = {
      def loop(i: Int, acc: List[A]): List[A] = {
        if (i < 0) acc else loop(i - 1, arr(i) :: acc)
      }
      loop(size - 1, Nil)
    }

    def outerToList(arr: Array[Array[A]], size: Int): List[List[A]] = {
      def loop(i: Int, acc: List[List[A]]): List[List[A]] = {
        if (i < 0) acc else loop(i - 1, arrayToList(arr(i), arr(i).length) :: acc)
      }
      loop(size - 1, Nil)
    }

    outerToList(outerArr, outerSize)
  }
}
