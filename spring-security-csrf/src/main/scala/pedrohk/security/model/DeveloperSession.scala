package pedrohk.security.model

  class DeveloperSession(
                          var owner: String,
                          var activeProject: String
                        ) {

    def this() = {
      this("", "")
    }
  }