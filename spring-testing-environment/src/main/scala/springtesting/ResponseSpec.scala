package springtesting {

  class ResponseSpec(private val response: Response) {
    
    def expectStatus: StatusAssertions = {
      new StatusAssertions(response, this)
    }

    def expectBody: BodyAssertions = {
      new BodyAssertions(response, this)
    }
  }

}
