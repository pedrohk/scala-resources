package pedrohk.springvalue.model

final class DeveloperProfile(
                              val owner: String,
                              val team: String,
                              val mentor: String,
                              val environment: String
                            ) {

  def profileKey(): String = {
    s"$owner|$team|$mentor|$environment"
  }

}