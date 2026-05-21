package springintegration

import java.util.concurrent.ConcurrentHashMap

class UserRepository {

  private val database: ConcurrentHashMap[String, User] = new ConcurrentHashMap[String, User]()

  def save(user: User): User = {
    database.put(user.name, user)
    user
  }

  def findByName(name: String): Option[User] = {
    Option(database.get(name))
  }

  def clear(): Unit = {
    database.clear()
  }

}