package springtesting

class WebTestClient(private val controller: ApiController) {
  
  def get(): UriSpec = {
    new UriSpec(controller, "GET")
  }

  def post(): UriSpec = {
    new UriSpec(controller, "POST")
  }
}

object WebTestClient {
  
  def bindToController(controller: ApiController): WebTestClient = {
    new WebTestClient(controller)
  }
}
