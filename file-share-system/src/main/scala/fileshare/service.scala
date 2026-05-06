package fileshare

import java.util.UUID

class FileService(repo: FileRepository) {

  def saveFile(name: String, content: String): FileData = {
    val encrypted = Crypto.encrypt(content.getBytes("UTF-8"))
    val file = FileData(UUID.randomUUID().toString, name, encrypted, false)
    repo.save(file)
    file
  }

  def restoreFile(id: String): Option[String] = {
    repo.find(id).filter(!_.deleted).map { file =>
      new String(Crypto.decrypt(file.encryptedContent), "UTF-8")
    }
  }

  def deleteFile(id: String): Boolean = {
    repo.find(id) match {
      case Some(file) if !file.deleted =>
        repo.update(file.copy(deleted = true))
        true
      case _ =>
        false
    }
  }

  def listFiles(): List[String] = {
    repo.all().filter(!_.deleted).map(_.name)
  }

  def search(term: String): List[String] = {
    repo.all()
      .filter(f => !f.deleted && f.name.toLowerCase.contains(term.toLowerCase))
      .map(_.name)
  }

  def size(): Int = {
    repo.all().count(!_.deleted)
  }
}