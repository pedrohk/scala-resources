package pedrohk.security.model

class DeveloperProfile(
                        var owner: String,
                        var specialization: String,
                        var activeProjects: Int
                      ) {

  def this() = {
    this("", "", 0)
  }
}