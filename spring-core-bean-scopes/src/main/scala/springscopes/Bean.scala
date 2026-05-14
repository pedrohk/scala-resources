package springscopes {

  trait Bean {
    def message: String
    def instanceId: String
  }

  class SingletonBean(val message: String) extends Bean {
    val instanceId: String = java.util.UUID.randomUUID().toString
  }

  class PrototypeBean(val message: String) extends Bean {
    val instanceId: String = java.util.UUID.randomUUID().toString
  }

}
