package pedrohk.beanprocessor.processor

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import pedrohk.beanprocessor.service.DeveloperProfileService

class ProfileInitializationPostProcessorTest
  extends AnyFunSuite
    with Matchers {

  test("ShouldUpdateOwnerBeforeInitialization") {

    val processor =
      new ProfileInitializationPostProcessor()

    val service =
      new DeveloperProfileService()

    processor.postProcessBeforeInitialization(
      service,
      "developerProfileService"
    )

    service.currentOwner() shouldBe
      "Pedro Henrique"
  }

  test("ShouldMarkBeanInitializedAfterInitialization") {

    val processor =
      new ProfileInitializationPostProcessor()

    val service =
      new DeveloperProfileService()

    processor.postProcessAfterInitialization(
      service,
      "developerProfileService"
    )

    service.isInitialized() shouldBe
      true
  }

  test("ShouldExecuteFullLifecycle") {

    val processor =
      new ProfileInitializationPostProcessor()

    val service =
      new DeveloperProfileService()

    processor.postProcessBeforeInitialization(
      service,
      "developerProfileService"
    )

    processor.postProcessAfterInitialization(
      service,
      "developerProfileService"
    )

    service.summary() shouldBe
      "Pedro Henrique:true"
  }

}