package grocery

import java.util.UUID

class GroceryService(repo: GroceryRepository) {

  def addItem(name: String): Item = {
    val item = Item(UUID.randomUUID().toString, name, Status.TODO)
    repo.add(item)
    item
  }

  def removeItem(id: String): Option[Item] = {
    repo.remove(id)
  }

  def markDone(id: String): Option[Item] = {
    repo.find(id).map { item =>
      val updated = item.copy(status = Status.DONE)
      repo.update(updated)
      updated
    }
  }

  def redo(id: String): Option[Item] = {
    repo.find(id).map { item =>
      val updated = item.copy(status = Status.TODO)
      repo.update(updated)
      updated
    }
  }

  def doItem(id: String): Option[Item] = {
    markDone(id)
  }

  def listAll(): List[Item] = {
    repo.all()
  }

  def size(): Int = {
    repo.size
  }
}