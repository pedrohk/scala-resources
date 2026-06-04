package pedrohk.injection.service

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import pedrohk.injection.repository.InMemoryProfileRepository

class ConstructorInjectedProfileServiceTest
  extends AnyFlatSpec
    with Matchers {

  "ConstructorInjectedProfileService" should "load existing profile" in {

    val service =
      new ConstructorInjectedProfileService(
        new InMemoryProfileRepository()
      )

    val profile =
      service.load(
        1L
      )

    profile.isDefined shouldBe true

    profile.get.name shouldBe
      "Pedro Henrique"
  }

  it should "return empty when profile does not exist" in {

    val service =
      new ConstructorInjectedProfileService(
        new InMemoryProfileRepository()
      )

    service
      .load(
        99L
      ) shouldBe None
  }

  it should "validate existence" in {

    val service =
      new ConstructorInjectedProfileService(
        new InMemoryProfileRepository()
      )

    service.exists(
      1L
    ) shouldBe true

    service.exists(
      200L
    ) shouldBe false
  }

}