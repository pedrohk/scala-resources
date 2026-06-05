package pedrohk.beanprocessor.model

class InitializationAudit(
                           val initialized: Boolean,
                           val owner: String
                         ) {

  def description(): String = {
    s"$owner:$initialized"
  }

}