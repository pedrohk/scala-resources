package P07

class P09 {

  def flatten(list: List[Any]): List[Any] = {
    var stackCapacity = 16
    var stackArr = new Array[Any](stackCapacity)
    var top = -1

    def ensureStackCapacity(): Unit = {
      if (top + 1 >= stackCapacity) {
        val newCapacity = stackCapacity * 2
        val newArr = new Array[Any](newCapacity)
        var i = 0
        while (i <= top) {
          newArr(i) = stackArr(i)
          i += 1
        }
        stackArr = newArr
        stackCapacity = newCapacity
      }
    }

    def push(value: Any): Unit = {
      ensureStackCapacity()
      top += 1
      stackArr(top) = value
    }

    def pop(): Any = {
      val value = stackArr(top)
      top -= 1
      value
    }

    def stackIsEmpty: Boolean = top < 0

    def pushListReversed(l: List[Any]): Unit = {
      def loop(remaining: List[Any]): Unit = {
        remaining match {
          case Nil    => ()
          case h :: t =>
            loop(t)
            push(h)
        }
      }
      loop(l)
    }

    pushListReversed(list)

    var resultCapacity = 16
    var resultArr = new Array[Any](resultCapacity)
    var resultSize = 0

    def addResult(value: Any): Unit = {
      if (resultSize >= resultCapacity) {
        val newCapacity = resultCapacity * 2
        val newArr = new Array[Any](newCapacity)
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

    while (!stackIsEmpty) {
      pop() match {
        case nested: List[Any] => pushListReversed(nested)
        case leaf               => addResult(leaf)
      }
    }

    def arrayToList(arr: Array[Any], size: Int): List[Any] = {
      def loop(i: Int, acc: List[Any]): List[Any] = {
        if (i < 0) acc
        else loop(i - 1, arr(i) :: acc)
      }
      loop(size - 1, Nil)
    }

    arrayToList(resultArr, resultSize)
  }
}
