package guitar

class GuitarService(factory: GuitarFactory, inventory: Inventory) {

  def createAndStore(model: Model, specs: Specs, os: String): Guitar = {
    val guitar = factory.build(model, specs, os)
    inventory.add(guitar)
    guitar
  }

  def remove(id: String): Option[Guitar] = {
    inventory.remove(id)
  }

  def find(id: String): Option[Guitar] = {
    inventory.findById(id)
  }

  def searchByModel(name: String): List[Guitar] = {
    inventory.findByModel(name)
  }

  def inventorySize(): Int = {
    inventory.size
  }
}