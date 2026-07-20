package P08

import scala.collection.mutable


class P04 {

  def compress[A](list: List[A]): List[A] = {
    val output = mutable.ListBuffer.empty[A]
    var remaining = list

    while (remaining.nonEmpty) {
      val head = remaining.head
      if (output.isEmpty || !sameAsLast(output, head)) {
        output += head
      }
      remaining = remaining.tail
    }

    output.toList
  }

  private def sameAsLast[A](buffer: mutable.ListBuffer[A], value: A): Boolean = {
    buffer(buffer.size - 1) == value
  }
}
