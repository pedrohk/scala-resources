package notes

import scala.collection.mutable

class NoteRepository {

  private val storage: mutable.Map[String, Note] = mutable.Map.empty

  def save(note: Note): Unit = {
    storage.put(note.id, note)
  }

  def find(id: String): Option[Note] = {
    storage.get(id)
  }

  def update(note: Note): Unit = {
    storage.put(note.id, note)
  }

  def delete(id: String): Boolean = {
    storage.get(id) match {
      case Some(note) =>
        storage.put(id, note.copy(deleted = true, status = SyncStatus.PENDING))
        true
      case None =>
        false
    }
  }

  def all(): List[Note] = {
    storage.values.toList
  }

  def size: Int = {
    storage.size
  }
}