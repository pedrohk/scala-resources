package grocery

import org.scalatest.funsuite.AnyFunSuite

class GrocerySpec extends AnyFunSuite {

  def newService(): GroceryService = {
    new GroceryService(new GroceryRepository)
  }

  test("add item increases size") {
    val service = newService()
    val before = service.size()
    service.addItem("Milk")
    val after = service.size()

    assert(after == before + 1)
  }

  test("remove item decreases size") {
    val service = newService()
    val item = service.addItem("Bread")
    val before = service.size()
    service.removeItem(item.id)
    val after = service.size()

    assert(after == before - 1)
  }

  test("mark item as done") {
    val service = newService()
    val item = service.addItem("Eggs")
    val updated = service.markDone(item.id)

    assert(updated.isDefined)
    assert(updated.get.status == Status.DONE)
  }

  test("redo item resets to todo") {
    val service = newService()
    val item = service.addItem("Butter")
    service.markDone(item.id)
    val updated = service.redo(item.id)

    assert(updated.isDefined)
    assert(updated.get.status == Status.TODO)
  }

  test("doItem behaves like markDone") {
    val service = newService()
    val item = service.addItem("Cheese")
    val updated = service.doItem(item.id)

    assert(updated.isDefined)
    assert(updated.get.status == Status.DONE)
  }

  test("list all returns all items") {
    val service = newService()
    service.addItem("Apple")
    service.addItem("Banana")

    val list = service.listAll()
    assert(list.size == 2)
  }

  test("find consistency after updates") {
    val service = newService()
    val item = service.addItem("Orange")
    service.markDone(item.id)

    val all = service.listAll()
    assert(all.head.status == Status.DONE)
  }

  test("remove non-existing returns none") {
    val service = newService()
    val result = service.removeItem("invalid")

    assert(result.isEmpty)
  }

  test("high volume items") {
    val service = newService()

    (1 to 1000).foreach { i =>
      service.addItem(s"item-$i")
    }

    assert(service.size() == 1000)
  }

  test("consistency multiple operations") {
    val service = newService()
    val item = service.addItem("Tomato")

    service.markDone(item.id)
    service.redo(item.id)
    service.markDone(item.id)

    val finalState = service.listAll().head.status
    assert(finalState == Status.DONE)
  }
}