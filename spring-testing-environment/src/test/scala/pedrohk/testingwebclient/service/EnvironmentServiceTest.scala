package pedrohk.testingwebclient.service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import org.springframework.core.env.StandardEnvironment

class EnvironmentServiceTest
  extends AnyFunSuite
    with Matchers {

  def shouldResolveEnvironmentValue(): Unit = {

    val env =
      new StandardEnvironment()

    env.getSystemProperties.put(
      "application.environment",
      "local"
    )

    val service =
      new EnvironmentService(
        env
      )

    service.currentEnvironment()
      .shouldBe(
        "local"
      )
  }

  test(
    "shouldResolveEnvironmentValue"
  ) {
    shouldResolveEnvironmentValue()
  }

  def shouldReturnLocalWhenMissing(): Unit = {

    val service =
      new EnvironmentService(
        new StandardEnvironment()
      )

    service.currentEnvironment()
      .shouldBe(
        "local"
      )
  }

  test(
    "shouldReturnLocalWhenMissing"
  ) {
    shouldReturnLocalWhenMissing()
  }

}