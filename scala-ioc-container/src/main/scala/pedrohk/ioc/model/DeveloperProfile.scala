package pedrohk.ioc.model

class DeveloperProfile(
                        val fullName: String,
                        val specialization: String
                      ) {

  def description: String = {
    s"$fullName works with $specialization"
  }

}