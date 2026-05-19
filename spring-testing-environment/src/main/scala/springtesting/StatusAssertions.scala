package springtesting {

  class StatusAssertions(private val response: Response, private val spec: ResponseSpec) {
    def isOk: ResponseSpec = {
      if (response.getStatus != 200) {
        throw new AssertionError(s"Expected status 200 but got ${response.getStatus}")
      }
      spec
    }

    def isForbidden: ResponseSpec = {
      if (response.getStatus != 403) {
        throw new AssertionError(s"Expected status 403 but got ${response.getStatus}")
      }
      spec
    }

    def isNotFound: ResponseSpec = {
      if (response.getStatus != 404) {
        throw new AssertionError(s"Expected status 404 but got ${response.getStatus}")
      }
      spec
    }
  }

}
