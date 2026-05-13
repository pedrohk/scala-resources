package ioc

@Component
class UserRepository {

  def findUser(id: Long): String = {
    s"user-$id"
  }

  def exists(id: Long): Boolean = {
    id > 0
  }
}