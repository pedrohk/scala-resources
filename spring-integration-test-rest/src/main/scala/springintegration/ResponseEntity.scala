package springintegration {

  class ResponseEntity[T](private val body: T, private val statusCode: Int) {

    def getBody: T = {
      this.body
    }

    def getStatusCode: Int = {
      this.statusCode
    }

  }

}
