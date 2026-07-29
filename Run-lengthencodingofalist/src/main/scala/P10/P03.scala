package P10

import scala.collection.mutable

class P03 {

  def encode[A](list: List[A]): List[(Int, A)] = {
    val output = mutable.ListBuffer.empty[(Int, A)]
    var remaining = list

    while (remaining.nonEmpty) {
      val head = remaining.head
      if (output.nonEmpty && output(output.size - 1)._2 == head) {
        val (count, value) = output(output.size - 1)
        output(output.size - 1) = (count + 1, value)
      } else {
        output += ((1, head))
      }
      remaining = remaining.tail
    }

    output.toList
  }
}
