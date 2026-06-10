package pedrohk.testingwebclient.controller

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import org.springframework.core.env.StandardEnvironment

import pedrohk.testingwebclient.service.EnvironmentService
import pedrohk.testingwebclient.service.ProfileService

class ProfileControllerTest
  extends AnyFunSuite
    with Matchers {

  private def controller(): ProfileController = {

    val env =
      new StandardEnvironment()

    env
      .getSystemProperties
      .put(
        "application.environment",
        "qa"
      )

    new ProfileController(
      new ProfileService(
        new EnvironmentService(
          env
        )
      )
    )
  }

  def shouldReturnCurrentProfile(): Unit = {

    val profile =
      controller()
        .current()

    profile.owner
      .shouldBe(
        "Pedro Henrique"
      )

    profile.environment
      .shouldBe(
        "qa"
      )
  }

  test(
    "shouldReturnCurrentProfile"
  ) {
    shouldReturnCurrentProfile()
  }

  def shouldReturnSummary(): Unit = {

    controller()
      .summary()
      .description
      .shouldBe(
        "Pedro Henrique-qa"
      )
  }

  test(
    "shouldReturnSummary"
  ) {
    shouldReturnSummary()
  }

}