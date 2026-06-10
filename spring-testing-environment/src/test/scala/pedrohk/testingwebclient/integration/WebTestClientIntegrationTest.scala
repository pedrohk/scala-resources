package pedrohk.testingwebclient.integration

import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.web.reactive.server.WebTestClient
import pedrohk.testingwebclient.Application

class WebTestClientIntegrationTest
  extends AnyFunSuite
    with Matchers
    with BeforeAndAfterAll {

  private val context:
    ConfigurableApplicationContext =
    SpringApplication.run(
      classOf[Application],
      "--server.port=8089",
      "--application.environment=web"
    )

  private val client =
    WebTestClient
      .bindToServer()
      .baseUrl(
        "http://localhost:8089"
      )
      .build()

  override def afterAll(): Unit = {
    context.close()
  }

  def shouldExposeCurrentEndpoint(): Unit = {

    client
      .get()
      .uri(
        "/profiles/current"
      )
      .exchange()
      .expectStatus()
      .isOk
  }

  test(
    "shouldExposeCurrentEndpoint"
  ) {
    shouldExposeCurrentEndpoint()
  }

  def shouldExposeSummaryEndpoint(): Unit = {

    client
      .get()
      .uri(
        "/profiles/summary"
      )
      .exchange()
      .expectStatus()
      .isOk
  }

  test(
    "shouldExposeSummaryEndpoint"
  ) {
    shouldExposeSummaryEndpoint()
  }

}