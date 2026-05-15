package springbpp {

  class GreetingService extends Initializable {
    private var initialized: Boolean = false
    private var prefix: String = ""

    def setPrefix(p: String): Unit = {
      this.prefix = p
    }

    def getPrefix: String = {
      this.prefix
    }

    def initialize(): Unit = {
      this.initialized = true
    }

    def isInitialized: Boolean = {
      this.initialized
    }

    def greet(name: String): String = {
      s"${prefix}${name}"
    }
  }

}
