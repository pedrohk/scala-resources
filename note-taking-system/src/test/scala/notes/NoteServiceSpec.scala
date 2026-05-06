package notes

import org.scalatest.funsuite.AnyFunSuite

class NoteServiceSpec extends AnyFunSuite {

  def newService(): NoteService = {
    new NoteService(new NoteRepository)
  }

  test("add note increases size") {
    val service = newService()
    val before = service.size()
    service.addNote("t", "c")
    val after = service.size()

    assert(after == before + 1)
  }

  test("edit note updates content") {
    val service = newService()
    val note = service.addNote("t", "c")

    val updated = service.editNote(note.id, "t2", "c2")

    assert(updated.isDefined)
    assert(updated.get.title == "t2")
    assert(updated.get.content == "c2")
  }

  test("delete note removes from active list") {
    val service = newService()
    val note = service.addNote("t", "c")

    val result = service.deleteNote(note.id)

    assert(result)
    assert(service.getNote(note.id).isEmpty)
  }

  test("list notes excludes deleted") {
    val service = newService()
    val n1 = service.addNote("a", "a")
    val n2 = service.addNote("b", "b")

    service.deleteNote(n1.id)

    val list = service.listNotes()
    assert(list.size == 1)
    assert(list.head.id == n2.id)
  }

  test("sync updates pending notes") {
    val service = newService()
    val n1 = service.addNote("a", "a")
    val n2 = service.addNote("b", "b")

    val synced = service.sync()

    assert(synced == 2)

    val notes = service.listNotes()
    assert(notes.forall(_.status == SyncStatus.SYNCED))
  }

  test("edit sets status to pending") {
    val service = newService()
    val note = service.addNote("t", "c")

    service.sync()

    val updated = service.editNote(note.id, "x", "y")

    assert(updated.isDefined)
    assert(updated.get.status == SyncStatus.PENDING)
  }

  test("delete sets pending before sync") {
    val service = newService()
    val note = service.addNote("t", "c")

    service.sync()
    service.deleteNote(note.id)

    val all = service.listNotes()
    assert(all.isEmpty)
  }

  test("sync after delete counts deleted too") {
    val service = newService()
    val note = service.addNote("t", "c")

    service.deleteNote(note.id)

    val count = service.sync()
    assert(count == 1)
  }

  test("high volume notes") {
    val service = newService()

    (1 to 1000).foreach { i =>
      service.addNote(s"t-$i", s"c-$i")
    }

    assert(service.size() == 1000)
  }

  test("consistency multiple syncs") {
    val service = newService()
    service.addNote("a", "a")

    val c1 = service.sync()
    val c2 = service.sync()

    assert(c1 == 1)
    assert(c2 == 0)
  }
}