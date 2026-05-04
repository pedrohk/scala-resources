package restaurant

import org.scalatest.funsuite.AnyFunSuite

class RestaurantQueueSpec extends AnyFunSuite {

  val pizza = Dish("Pizza", 10)
  val burger = Dish("Burger", 5)
  val pasta = Dish("Pasta", 8)

  val estimator = new TimeEstimator

  test("single dish time calculation") {
    val order = Order(List(OrderItem(pizza, 1)))
    val result = estimator.estimateOrderTime(order)
    assert(result == 10)
  }

  test("multiple items in order") {
    val order = Order(List(
      OrderItem(pizza, 1),
      OrderItem(burger, 2)
    ))
    val result = estimator.estimateOrderTime(order)
    assert(result == 10 + (5 * 2))
  }

  test("empty order") {
    val order = Order(List())
    val result = estimator.estimateOrderTime(order)
    assert(result == 0)
  }

  test("queue total time") {
    val queue = new KitchenQueue

    val order1 = Order(List(OrderItem(pizza, 1)))
    val order2 = Order(List(OrderItem(burger, 2)))

    queue.enqueue(order1)
    queue.enqueue(order2)

    val result = estimator.estimateQueueTime(queue)
    assert(result == 10 + (5 * 2))
  }

  test("wait time includes current queue") {
    val queue = new KitchenQueue

    val order1 = Order(List(OrderItem(pizza, 1)))
    val newOrder = Order(List(OrderItem(pasta, 1)))

    queue.enqueue(order1)

    val result = estimator.estimateWaitTime(queue, newOrder)
    assert(result == 10 + 8)
  }

  test("dequeue reduces queue time") {
    val queue = new KitchenQueue

    val order1 = Order(List(OrderItem(pizza, 1)))
    val order2 = Order(List(OrderItem(burger, 1)))

    queue.enqueue(order1)
    queue.enqueue(order2)

    val before = estimator.estimateQueueTime(queue)
    queue.dequeue()
    val after = estimator.estimateQueueTime(queue)

    assert(before > after)
  }

  test("high volume queue") {
    val queue = new KitchenQueue

    (1 to 100).foreach { _ =>
      queue.enqueue(Order(List(OrderItem(burger, 1))))
    }

    val result = estimator.estimateQueueTime(queue)
    assert(result == 100 * 5)
  }

  test("mixed complex order") {
    val order = Order(List(
      OrderItem(pizza, 2),
      OrderItem(burger, 3),
      OrderItem(pasta, 1)
    ))

    val result = estimator.estimateOrderTime(order)
    assert(result == (2 * 10) + (3 * 5) + 8)
  }

  test("queue pending orders integrity") {
    val queue = new KitchenQueue

    val order1 = Order(List(OrderItem(pizza, 1)))
    val order2 = Order(List(OrderItem(burger, 1)))

    queue.enqueue(order1)
    queue.enqueue(order2)

    val list = queue.pendingOrders
    assert(list.size == 2)
  }

  test("consistency across multiple estimations") {
    val queue = new KitchenQueue

    val order = Order(List(OrderItem(pasta, 2)))
    queue.enqueue(order)

    val r1 = estimator.estimateQueueTime(queue)
    val r2 = estimator.estimateQueueTime(queue)

    assert(r1 == r2)
  }
}