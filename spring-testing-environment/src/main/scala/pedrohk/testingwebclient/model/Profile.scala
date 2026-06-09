package pedrohk.testingwebclient.model

case class Profile(
                    id: Long,
                    owner: String,
                    environment: String,
                    active: Boolean
                  )