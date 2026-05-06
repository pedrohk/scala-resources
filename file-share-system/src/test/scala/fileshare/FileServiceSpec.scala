package fileshare

import org.scalatest.funsuite.AnyFunSuite

class FileServiceSpec extends AnyFunSuite {

  def newService(): FileService = {
    new FileService(new FileRepository)
  }

  test("save and restore file") {
    val service = newService()

    val file = service.saveFile("doc.txt", "hello")
    val content = service.restoreFile(file.id)

    assert(content.isDefined)
    assert(content.get == "hello")
  }

  test("encryption actually changes content") {
    val repo = new FileRepository
    val service = new FileService(repo)

    val file = service.saveFile("secret.txt", "data")
    val stored = repo.find(file.id).get

    assert(!new String(stored.encryptedContent, "UTF-8").contains("data"))
  }

  test("delete file prevents restore") {
    val service = newService()

    val file = service.saveFile("a.txt", "abc")
    service.deleteFile(file.id)

    val restored = service.restoreFile(file.id)
    assert(restored.isEmpty)
  }

  test("list files excludes deleted") {
    val service = newService()

    val f1 = service.saveFile("a.txt", "a")
    val f2 = service.saveFile("b.txt", "b")

    service.deleteFile(f1.id)

    val list = service.listFiles()
    assert(list == List("b.txt"))
  }

  test("search finds correct files") {
    val service = newService()

    service.saveFile("notes.txt", "x")
    service.saveFile("image.png", "y")

    val result = service.search("note")
    assert(result.contains("notes.txt"))
    assert(!result.contains("image.png"))
  }

  test("search is case insensitive") {
    val service = newService()

    service.saveFile("Report.pdf", "x")

    val result = service.search("report")
    assert(result.contains("Report.pdf"))
  }

  test("delete non-existing returns false") {
    val service = newService()

    val result = service.deleteFile("invalid")
    assert(!result)
  }

  test("size counts only active files") {
    val service = newService()

    val f1 = service.saveFile("a.txt", "a")
    val f2 = service.saveFile("b.txt", "b")

    service.deleteFile(f1.id)

    assert(service.size() == 1)
  }

  test("high volume files") {
    val service = newService()

    (1 to 1000).foreach { i =>
      service.saveFile(s"file-$i", "x")
    }

    assert(service.size() == 1000)
  }

  test("consistency restore multiple times") {
    val service = newService()

    val file = service.saveFile("x.txt", "data")

    val r1 = service.restoreFile(file.id)
    val r2 = service.restoreFile(file.id)

    assert(r1 == r2)
  }
}