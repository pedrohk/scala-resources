package pedrohk.beanprocessor.service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class DeveloperProfileServiceTest
  extends AnyFunSuite
    with Matchers {

  test("ShouldStartWithDefaultValues") {

    val service =
      new DeveloperProfileService()

    service.currentOwner() shouldBe
      "unknown"

    service.isInitialized() shouldBe
      false
  }

  test("ShouldAllowOwnderUpdate") {

    val service =
      new DeveloperProfileService()

    service.setOwner(
      "Pedro Henrique"
    )

    service.currentOwner() shouldBe
      "Pedro Henrique"
  }

  test("ShouldAllowInitializationUpdate") {

    val service =
      new DeveloperProfileService()

    service.setInitialized(
      true
    )

    service.isInitialized() shouldBe
      true
  }

  test("ShouldGenerateSummary") {

    val service =
      new DeveloperProfileService()

    service.setOwner(
      "Lia"
    )

    service.setInitialized(
      true
    )

    service.summary() shouldBe
      "Lia:true"
  }

}