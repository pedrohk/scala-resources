package notes

import java.util.UUID

class NoteService(repo: NoteRepository) {

  def addNote(title: String, content: String): Note = {
    val note = Note(UUID.randomUUID().toString, title, content, false, SyncStatus.PENDING)
    repo.save(note)
    note
  }

  def editNote(id: String, title: String, content: String): Option[Note] = {
    repo.find(id).filter(!_.deleted).map { note =>
      val updated = note.copy(title = title, content = content, status = SyncStatus.PENDING)
      repo.update(updated)
      updated
    }
  }

  def deleteNote(id: String): Boolean = {
    repo.delete(id)
  }

  def getNote(id: String): Option[Note] = {
    repo.find(id).filter(!_.deleted)
  }

  def listNotes(): List[Note] = {
    repo.all().filter(!_.deleted)
  }

  def sync(): Int = {
    val pending = repo.all().filter(_.status == SyncStatus.PENDING)
    pending.foreach { note =>
      repo.update(note.copy(status = SyncStatus.SYNCED))
    }
    pending.size
  }

  def size(): Int = {
    listNotes().size
  }
}