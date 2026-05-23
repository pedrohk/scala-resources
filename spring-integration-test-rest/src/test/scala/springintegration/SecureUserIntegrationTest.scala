package springintegration {

  import org.scalatest.funsuite.AnyFunSuite
  import org.scalatest.matchers.should.Matchers
  import org.scalatest.BeforeAndAfterEach

  class SecureUserIntegrationTest extends AnyFunSuite with Matchers with BeforeAndAfterEach {

    private val repository = new UserRepository()
    private val securityContext = new SecurityContext()
    private val controller = new UserController(repository, securityContext)
    private val restTemplate = new TestRestTemplate(controller)

    override def beforeEach(): Unit = {
      repository.clear()
    }

    test("Requests without authentication should be rejected with 401 Unauthorized by security filter") {
      val response = restTemplate.getForEntity("/api/users/Pedro%20Henrique")
      response.getStatusCode shouldBe 401
      response.getBody should include("Unauthorized")
    }

    test("Requests with wrong password should be rejected with 401 Unauthorized") {
      val securedClient = restTemplate.withBasicAuth("Pedro Henrique", "wrong_pass")
      val response = securedClient.getForEntity("/api/users/Pedro%20Henrique")
      response.getStatusCode shouldBe 401
    }

    test("Admin endpoint should grant access to Pedro Henrique when authenticated") {
      val adminClient = restTemplate.withBasicAuth("Pedro Henrique", "secret123")
      val response = adminClient.getForEntity("/api/admin")

      response.getStatusCode shouldBe 200
      response.getBody should include("Welcome Admin Pedro Henrique")
    }

    test("Admin endpoint should return 403 Forbidden when accessed by non-admin user Lia") {
      val userClient = restTemplate.withBasicAuth("Lia", "secret123")
      val response = userClient.getForEntity("/api/admin")

      response.getStatusCode shouldBe 403
      response.getBody should include("Forbidden")
    }

    test("Authenticated TestRestTemplate should successfully register Lia inside database layer") {
      val client = restTemplate.withBasicAuth("Lia", "secret123")
      val payload = """{"name":"Lia","role":"Supervisor"}"""

      val postResponse = client.postForEntity("/api/users", payload)
      postResponse.getStatusCode shouldBe 201

      val getResponse = client.getForEntity("/api/users/Lia")
      getResponse.getStatusCode shouldBe 200
      getResponse.getBody should include("Lia")
      getResponse.getBody should include("Supervisor")
    }

  }
}
