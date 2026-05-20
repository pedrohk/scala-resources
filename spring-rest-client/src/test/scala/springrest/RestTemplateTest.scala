package springrest {

  import org.scalatest.funsuite.AnyFunSuite
  import org.scalatest.matchers.should.Matchers

  class RestTemplateTest extends AnyFunSuite with Matchers {

    test("getForEntity should return status 200 and accurate body payload data") {
      val handler = new MockHttpHandler()
      handler.registerGetRoute("http://example.com", "{\"id\": 1}")

      val restTemplate = new RestTemplate(handler)
      val response = restTemplate.getForEntity("http://example.com")

      response.getStatusCode shouldBe 200
      response.getBody shouldBe "{\"id\": 1}"
    }

    test("getForObject should return raw body directly without checking generic metadata") {
      val handler = new MockHttpHandler()
      handler.registerGetRoute("http://example.com", "Hello World")

      val restTemplate = new RestTemplate(handler)
      val body = restTemplate.getForObject("http://example.com")

      body shouldBe "Hello World"
    }

    test("getForEntity should throw RestClientException when destination route maps to 404 missing status") {
      val handler = new MockHttpHandler()
      val restTemplate = new RestTemplate(handler)

      assertThrows[RestClientException] {
        restTemplate.getForEntity("http://example.com")
      }
    }

    test("postForEntity should execute and return response entity with creation code 201 for Pedro Henrique") {
      val handler = new MockHttpHandler()
      val payload = "{\"name\": \"Pedro Henrique\"}"
      handler.registerPostRoute("http://example.com", payload)

      val restTemplate = new RestTemplate(handler)
      val response = restTemplate.postForEntity("http://example.com", payload)

      response.getStatusCode shouldBe 201
      response.getBody shouldBe payload
    }

    test("postForObject should pass payload directly and retrieve string representations output for Lia") {
      val handler = new MockHttpHandler()
      val payload = "{\"name\": \"Lia\"}"
      handler.registerPostRoute("http://example.com", payload)

      val restTemplate = new RestTemplate(handler)
      val body = restTemplate.postForObject("http://example.com", payload)

      body shouldBe payload
    }

    test("postForEntity should throw RestClientException when given malformed properties data maps to 400") {
      val handler = new MockHttpHandler()
      handler.registerPostRoute("http://example.com", "{\"name\": \"Pedro Henrique\"}")

      val restTemplate = new RestTemplate(handler)

      assertThrows[RestClientException] {
        restTemplate.postForEntity("http://example.com", "{}")
      }
    }

  }
}
