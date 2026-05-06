package fileshare

import scala.collection.mutable

class FileRepository {

  private val storage: mutable.Map[String, FileData] = mutable.Map.empty

  def save(file: FileData): Unit = {
    storage.put(file.id, file)
  }

  def find(id: String): Option[FileData] = {
    storage.get(id)
  }

  def update(file: FileData): Unit = {
    storage.put(file.id, file)
  }

  def all(): List[FileData] = {
    storage.values.toList
  }

  def size: Int = {
    storage.size
  }
}