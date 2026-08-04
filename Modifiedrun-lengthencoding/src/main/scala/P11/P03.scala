package P11

import scala.collection.mutable

class P03 {

  def encodeModified[A](list: List[A]): List[Any] = {
    val output = mutable.ListBuffer.empty[Any]
    var pendingValue: A = null.asInstanceOf[A]
    var pendingCount = 0
    var hasPending = false
    var remaining = list

    while (remaining.nonEmpty) {
      val head = remaining.head
      if (hasPending && pendingValue == head) {
        pendingCount += 1
      } else {
        if (hasPending) {
          output += toModified(pendingCount, pendingValue)
        }
        pendingValue = head
        pendingCount = 1
        hasPending = true
      }
      remaining = remaining.tail
    }

    if (hasPending) {
      output += toModified(pendingCount, pendingValue)
    }

    output.toList
  }

  private def toModified[A](count: Int, value: A): Any = {
    if (count == 1) value else (count, value)
  }
}
