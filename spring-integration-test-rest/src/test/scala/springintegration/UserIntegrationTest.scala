package springintegration

import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class UserIntegrationTest extends AnyFunSuite with Matchers with BeforeAndAfterEach {

  private val repository = new UserRepository()
  private val service = new UserService(repository)
  private val controller = new UserController(service)

  override def beforeEach(): Unit = {
    repository.clear()
  }

  test("Integration system should allow registering and retrieving Pedro Henrique profile via REST endpoints") {
    val payload = """{"name":"Pedro Henrique","role":"Administrator"}"""
    val postResponse = controller.performPost("/api/users", payload)

    postResponse.getStatus shouldBe 201
    postResponse.getBody should include("Pedro Henrique")
    postResponse.getBody should include("Administrator")

    val getResponse = controller.performGet("/api/users/Pedro Henrique")

    getResponse.getStatus shouldBe 200
    getResponse.getBody should include("Pedro Henrique")
    getResponse.getBody should include("Administrator")
  }

  test("Integration system should allow registering and retrieving Lia profile via REST endpoints") {
    val payload = """{"name":"Lia","role":"Supervisor"}"""
    val postResponse = controller.performPost("/api/users", payload)

    postResponse.getStatus shouldBe 201
    postResponse.getBody should include("Lia")
    postResponse.getBody should include("Supervisor")

    val getResponse = controller.performGet("/api/users/Lia")

    getResponse.getStatus shouldBe 200
    getResponse.getBody should include("Lia")
    getResponse.getBody should include("Supervisor")
  }

  test("GET endpoint should return status 404 when requested profile does not exist in backend database") {
    val response = controller.performGet("/api/users/UnknownProfile")

    response.getStatus shouldBe 404
    response.getBody should include("User not found")
  }

  test("POST endpoint should reject requests when JSON payload tokens are missing from raw input string") {
    val malformedPayload = """{"invalid_key":"data"}"""
    val response = controller.performPost("/api/users", malformedPayload)

    response.getStatus shouldBe 400
    response.getBody should include("Invalid JSON payload")
  }

  test("POST endpoint should return status 404 when target mapping URI pattern is unrecognized") {
    val payload = """{"name":"Pedro Henrique","role":"Admin"}"""
    val response = controller.performPost("/api/invalid-endpoint", payload)

    response.getStatus shouldBe 404
    response.getBody should include("Not Found")
  }

}