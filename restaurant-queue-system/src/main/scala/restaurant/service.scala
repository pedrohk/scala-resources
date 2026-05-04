package restaurant

class TimeEstimator {

  def estimateOrderTime(order: Order): Int = {
    order.items.map(item => item.dish.preparationTime * item.quantity).sum
  }

  def estimateQueueTime(queue: KitchenQueue): Int = {
    queue.pendingOrders.map(o => estimateOrderTime(o)).sum
  }

  def estimateWaitTime(queue: KitchenQueue, newOrder: Order): Int = {
    estimateQueueTime(queue) + estimateOrderTime(newOrder)
  }
}