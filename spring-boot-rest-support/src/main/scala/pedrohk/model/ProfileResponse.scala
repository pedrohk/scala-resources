package pedrohk.model

case class ProfileResponse(
                            identifier: Long,
                            displayName: String,
                            specialty: String,
                            enabled: Boolean
                          )