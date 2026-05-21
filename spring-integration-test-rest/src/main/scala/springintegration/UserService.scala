package springintegration

class UserService(private val userRepository: UserRepository) {

  def registerUser(name: String, role: String): User = {
    if (name == null || name.trim.isEmpty) {
      throw new IllegalArgumentException("Name cannot be empty")
    }
    val user = User(name, role)
    userRepository.save(user)
  }

  def getUserProfile(name: String): Option[User] = {
    userRepository.findByName(name)
  }

}