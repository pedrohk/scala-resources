package guitar

import org.scalatest.funsuite.AnyFunSuite

class GuitarFactorySpec extends AnyFunSuite {

  val factory = new GuitarFactory
  val inventory = new Inventory
  val service = new GuitarService(factory, inventory)

  val strat = Model("Stratocaster", GuitarType.ELECTRIC)
  val lesPaul = Model("Les Paul", GuitarType.ELECTRIC)

  val specs1 = Specs("Alder", 3, true)
  val specs2 = Specs("Mahogany", 2, false)

  test("create guitar and store in inventory") {
    val guitar = service.createAndStore(strat, specs1, "v1")

    assert(guitar.model.name == "Stratocaster")
    assert(service.inventorySize() == 1)
  }

  test("unique ids for guitars") {
    val g1 = service.createAndStore(strat, specs1, "v1")
    val g2 = service.createAndStore(strat, specs1, "v1")

    assert(g1.id != g2.id)
  }

  test("find guitar by id") {
    val guitar = service.createAndStore(lesPaul, specs2, "v2")
    val found = service.find(guitar.id)

    assert(found.isDefined)
    assert(found.get.id == guitar.id)
  }

  test("remove guitar from inventory") {
    val guitar = service.createAndStore(strat, specs1, "v1")
    val removed = service.remove(guitar.id)

    assert(removed.isDefined)
    assert(service.inventorySize() >= 0)
  }

  test("search by model") {
    service.createAndStore(strat, specs1, "v1")
    service.createAndStore(strat, specs2, "v2")
    service.createAndStore(lesPaul, specs2, "v2")

    val results = service.searchByModel("Stratocaster")
    assert(results.nonEmpty)
    assert(results.forall(_.model.name == "Stratocaster"))
  }

  test("inventory size increases") {
    val before = service.inventorySize()
    service.createAndStore(strat, specs1, "v1")
    val after = service.inventorySize()

    assert(after == before + 1)
  }

  test("remove non-existing returns none") {
    val result = service.remove("invalid-id")
    assert(result.isEmpty)
  }

  test("inventory all consistency") {
    val all = inventory.all()
    assert(all.size == inventory.size)
  }

  test("multiple guitars stored") {
    (1 to 50).foreach { _ =>
      service.createAndStore(strat, specs1, "v1")
    }

    assert(service.inventorySize() >= 50)
  }

  test("specs preserved correctly") {
    val guitar = service.createAndStore(lesPaul, specs2, "v3")

    assert(guitar.specs.wood == "Mahogany")
    assert(guitar.specs.pickups == 2)
    assert(!guitar.specs.hasTremolo)
  }
}