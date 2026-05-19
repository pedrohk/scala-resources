package springtesting

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class EnvironmentAndWebTestClientTest extends AnyFunSuite with Matchers {

  test("Environment should store properties and profiles accurately") {
    val env = new Environment()
    env.setProperty("server.port", "8080")
    env.addActiveProfile("test")

    env.getProperty("server.port") shouldBe "8080"
    env.getProperty("missing.prop", "default") shouldBe "default"
    env.acceptsProfiles("test") shouldBe true
    env.acceptsProfiles("prod") shouldBe false
  }

  test("WebTestClient should assert successful response based on environment properties") {
    val env = new Environment()
    env.setProperty("app.name", "ScalaSpringEngine")
    env.setProperty("app.stage", "staging")

    val controller = new ApiController(env)
    val client = WebTestClient.bindToController(controller)

    client.get().uri("/info")
      .expectStatus.isOk
      .expectBody.equalsTo("App: ScalaSpringEngine, Stage: staging")
  }

  test("WebTestClient should verify forbidden status when active profiles deny production requests") {
    val env = new Environment()
    env.addActiveProfile("prod")

    val controller = new ApiController(env)
    val client = WebTestClient.bindToController(controller)

    client.get().uri("/admin")
      .expectStatus.isForbidden
      .expectBody.contains("Access denied")
  }

  test("WebTestClient should verify authorized status when active profile allows access") {
    val env = new Environment()
    env.addActiveProfile("dev")

    val controller = new ApiController(env)
    val client = WebTestClient.bindToController(controller)

    client.get().uri("/admin")
      .expectStatus.isOk
      .expectBody.equalsTo("Admin access granted for non-prod profile")
  }

  test("WebTestClient should verify missing endpoint mappings yield not found errors") {
    val env = new Environment()
    val controller = new ApiController(env)
    val client = WebTestClient.bindToController(controller)

    client.get().uri("/invalid-route")
      .expectStatus.isNotFound
      .expectBody.equalsTo("Not Found")
  }

  test("Assertion frameworks should throw clean exceptions on validation failure mismatch") {
    val env = new Environment()
    val controller = new ApiController(env)
    val client = WebTestClient.bindToController(controller)

    assertThrows[AssertionError] {
      client.get().uri("/invalid-route").expectStatus.isOk
    }

    assertThrows[AssertionError] {
      client.get().uri("/info").expectBody.equalsTo("Mismatched Value")
    }
  }
}
