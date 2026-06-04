package pedrohk.injection.service

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import pedrohk.injection.repository.InMemoryProfileRepository

class SetterInjectedProfileServiceTest
  extends AnyFlatSpec
    with Matchers {

  private def create():
  SetterInjectedProfileService = {

    val service =
      new SetterInjectedProfileService()

    service.setRepository(
      new InMemoryProfileRepository()
    )

    service
  }

  "SetterInjectedProfileService" should "load profile" in {

    val service =
      create()

    service
      .load(
        1L
      )
      .get
      .name shouldBe
      "Pedro Henrique"
  }

  it should "return mentor" in {

    val service =
      create()

    service
      .mentor(
        1L
      ) shouldBe
      Some(
        "Lia"
      )
  }

  it should "return none for unknown profile" in {

    val service =
      create()

    service
      .mentor(
        999L
      ) shouldBe None
  }

}