package P07

import scala.collection.mutable

class P03 {

  def flatten(list: List[Any]): List[Any] = {
    val stack = mutable.Stack.empty[Any]
    pushReversed(list, stack)

    val output = mutable.ListBuffer.empty[Any]
    while (stack.nonEmpty) {
      stack.pop() match {
        case nested: List[Any] => pushReversed(nested, stack)
        case leaf               => output += leaf
      }
    }
    output.toList
  }
  
  private def pushReversed(list: List[Any], stack: mutable.Stack[Any]): Unit = {
    def loop(l: List[Any]): Unit = {
      l match {
        case Nil =>
          ()
        case h :: t =>
          loop(t)
          stack.push(h)
      }
    }
    loop(list)
  }
}
