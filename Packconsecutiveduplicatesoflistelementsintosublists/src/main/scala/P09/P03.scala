package P09

import scala.collection.mutable

class P03 {

  def pack[A](list: List[A]): List[List[A]] = {
    val output = mutable.ListBuffer.empty[mutable.ListBuffer[A]]
    var remaining = list

    while (remaining.nonEmpty) {
      val head = remaining.head
      if (output.isEmpty || !sameAsLastGroup(output, head)) {
        output += mutable.ListBuffer(head)
      } else {
        output(output.size - 1) += head
      }
      remaining = remaining.tail
    }

    convert(output.toList)
  }

  private def sameAsLastGroup[A](output: mutable.ListBuffer[mutable.ListBuffer[A]], value: A): Boolean = {
    val lastGroup = output(output.size - 1)
    lastGroup(lastGroup.size - 1) == value
  }

  private def convert[A](groups: List[mutable.ListBuffer[A]]): List[List[A]] = {
    groups match {
      case Nil    => Nil
      case h :: t => h.toList :: convert(t)
    }
  }
}
