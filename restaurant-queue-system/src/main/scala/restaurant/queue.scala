package restaurant

import scala.collection.mutable.Queue

class KitchenQueue {

  private val queue: Queue[Order] = Queue.empty

  def enqueue(order: Order): Unit = {
    queue.enqueue(order)
  }

  def dequeue(): Option[Order] = {
    if (queue.nonEmpty) {
      Some(queue.dequeue())
    } else {
      None
    }
  }

  def pendingOrders: List[Order] = {
    queue.toList
  }
}