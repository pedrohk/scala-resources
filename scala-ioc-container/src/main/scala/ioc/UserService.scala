package ioc

@Component
class UserService(
                   val userRepository: UserRepository
                 ) {

  def getUser(id: Long): String = {
    userRepository.findUser(id)
  }

  def userExists(id: Long): Boolean = {
    userRepository.exists(id)
  }
}