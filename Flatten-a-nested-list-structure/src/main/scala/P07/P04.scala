package P07

import scala.collection.mutable


class P04 {

  def flatten(list: List[Any]): List[Any] = {
    val deque = mutable.ArrayDeque.empty[Any]
    appendAllToBack(list, deque)

    val output = mutable.ListBuffer.empty[Any]
    while (deque.nonEmpty) {
      deque.removeHead() match {
        case nested: List[Any] => prependAllToFront(nested, deque)
        case leaf               => output += leaf
      }
    }
    output.toList
  }

  private def appendAllToBack(list: List[Any], deque: mutable.ArrayDeque[Any]): Unit = {
    list match {
      case Nil    => ()
      case h :: t =>
        deque.append(h)
        appendAllToBack(t, deque)
    }
  }

  private def prependAllToFront(list: List[Any], deque: mutable.ArrayDeque[Any]): Unit = {
    def loop(l: List[Any]): Unit = {
      l match {
        case Nil    => ()
        case h :: t =>
          loop(t)
          deque.prepend(h)
      }
    }
    loop(list)
  }
}
