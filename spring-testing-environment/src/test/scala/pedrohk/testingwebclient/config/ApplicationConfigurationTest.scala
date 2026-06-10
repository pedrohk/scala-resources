package pedrohk.testingwebclient.config

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ApplicationConfigurationTest
  extends AnyFunSuite
    with Matchers {

  def shouldCreateOwnerBean(): Unit = {

    val config =
      new ApplicationConfiguration()

    config
      .ownerName()
      .shouldBe(
        "Lia"
      )
  }

  test(
    "shouldCreateOwnerBean"
  ) {
    shouldCreateOwnerBean()
  }

}