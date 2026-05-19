package springtesting {

  class UriSpec(private val controller: ApiController, private val method: String) {
    
    def uri(path: String): ResponseSpec = {
      val response = controller.handleRequest(path, method)
      new ResponseSpec(response)
    }
  }

}
