package grocery

import scala.collection.mutable

class GroceryRepository {

  private val items: mutable.Map[String, Item] = mutable.Map.empty

  def add(item: Item): Unit = {
    items.put(item.id, item)
  }

  def remove(id: String): Option[Item] = {
    items.remove(id)
  }

  def update(item: Item): Unit = {
    items.put(item.id, item)
  }

  def find(id: String): Option[Item] = {
    items.get(id)
  }

  def all(): List[Item] = {
    items.values.toList
  }

  def size: Int = {
    items.size
  }
}