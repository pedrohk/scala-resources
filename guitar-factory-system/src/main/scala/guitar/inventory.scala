package guitar

import scala.collection.mutable

class Inventory {

  private val items: mutable.Map[String, Guitar] = mutable.Map.empty

  def add(guitar: Guitar): Unit = {
    items.put(guitar.id, guitar)
  }

  def remove(id: String): Option[Guitar] = {
    items.remove(id)
  }

  def findById(id: String): Option[Guitar] = {
    items.get(id)
  }

  def findByModel(name: String): List[Guitar] = {
    items.values.filter(g => g.model.name == name).toList
  }

  def all(): List[Guitar] = {
    items.values.toList
  }

  def size: Int = {
    items.size
  }
}