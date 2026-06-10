package pedrohk.testingwebclient.service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import org.springframework.core.env.StandardEnvironment

class ProfileServiceTest
  extends AnyFunSuite
    with Matchers {

  private def create(
                      environmentName: String
                    ): ProfileService = {

    val env =
      new StandardEnvironment()

    env.getSystemProperties.put(
      "application.environment",
      environmentName
    )

    new ProfileService(
      new EnvironmentService(
        env
      )
    )
  }

  def shouldGenerateProfile(): Unit = {

    val result =
      create(
        "staging"
      )
        .profile()

    result.id
      .shouldBe(
        10L
      )

    result.owner
      .shouldBe(
        "Pedro Henrique"
      )

    result.environment
      .shouldBe(
        "staging"
      )

    result.active
      .shouldBe(
        true
      )
  }

  test(
    "shouldGenerateProfile"
  ) {
    shouldGenerateProfile()
  }

  def shouldGenerateProfileSummary(): Unit = {

    val result =
      create(
        "production"
      )
        .summary()

    result.description
      .shouldBe(
        "Pedro Henrique-production"
      )
  }

  test(
    "shouldGenerateProfileSummary"
  ) {
    shouldGenerateProfileSummary()
  }

}