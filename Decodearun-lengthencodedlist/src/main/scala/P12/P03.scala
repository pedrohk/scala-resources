package P12

import scala.collection.mutable

class P03 {

  def decode[A](list: List[(Int, A)]): List[A] = {
    val output = mutable.ListBuffer.empty[A]
    var remaining = list

    while (remaining.nonEmpty) {
      val (count, value) = remaining.head
      var i = 0
      while (i < count) {
        output += value
        i += 1
      }
      remaining = remaining.tail
    }

    output.toList
  }
}
