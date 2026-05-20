package springrest

class RestTemplate(private val httpHandler: MockHttpHandler) {

  def getForObject(url: String): String = {
    getForEntity(url).getBody
  }

  def getForEntity(url: String): ResponseEntity[String] = {
    val response = httpHandler.executeGet(url)
    if (response.getStatusCode != 200) {
      throw new RestClientException(s"GET request failed with status: ${response.getStatusCode}")
    }
    response
  }

  def postForObject(url: String, request: String): String = {
    val response = postForEntity(url, request)
    response.getBody
  }

  def postForEntity(url: String, request: String): ResponseEntity[String] = {
    val response = httpHandler.executePost(url, request)
    if (response.getStatusCode != 201) {
      throw new RestClientException(s"POST request failed with status: ${response.getStatusCode}")
    }
    response
  }

}