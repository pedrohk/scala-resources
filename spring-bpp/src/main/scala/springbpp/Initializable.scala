package springbpp {

  trait Initializable {
    def initialize(): Unit
    def isInitialized: Boolean
  }

}
