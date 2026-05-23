package springintegration {

  import java.util.Base64
  import java.nio.charset.StandardCharsets

  class TestRestTemplate(private val controller: UserController) {

    private var authHeader: String = null

    def withBasicAuth(username: String, password: String): TestRestTemplate = {
      val credentials = s"$username:$password"
      val encoded = Base64.getEncoder.encodeToString(credentials.getBytes(StandardCharsets.UTF_8))
      val client = new TestRestTemplate(controller)
      client.authHeader = s"Basic $encoded"
      client
    }

    def getForEntity(url: String): ResponseEntity[String] = {
      controller.handleRequest("GET", url, authHeader, "")
    }

    def postForEntity(url: String, requestBody: String): ResponseEntity[String] = {
      controller.handleRequest("POST", url, authHeader, requestBody)
    }

  }

}
