package pedrohk.model

case class Profile(
                    id: Long,
                    owner: String,
                    expertise: String,
                    active: Boolean
                  )