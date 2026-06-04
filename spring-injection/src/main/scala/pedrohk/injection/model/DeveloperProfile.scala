package pedrohk.injection.model

class DeveloperProfile(
                        val id: Long,
                        val name: String,
                        val mentor: String
                      ) {

  def summary(): String = {
    s"$id:$name:$mentor"
  }

}