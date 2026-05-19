package springtesting {

  class BodyAssertions(private val response: Response, private val spec: ResponseSpec) {
    
    def equalsTo(expectedBody: String): ResponseSpec = {
      if (response.getBody != expectedBody) {
        throw new AssertionError(s"Expected body '$expectedBody' but got '${response.getBody}'")
      }
      spec
    }

    def contains(substring: String): ResponseSpec = {
      if (!response.getBody.contains(substring)) {
        throw new AssertionError(s"Expected body to contain '$substring' but was '${response.getBody}'")
      }
      spec
    }
  }

}
