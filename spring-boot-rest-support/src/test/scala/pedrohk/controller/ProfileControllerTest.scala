package pedrohk.controller

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import pedrohk.model.Profile
import pedrohk.service.ProfileService

class FakeProfileService extends ProfileService(null) {

  override def retrieveProfile(identifier: Long): Profile = {
    Profile(
      identifier,
      "Pedro Henrique",
      "Spring Boot",
      true
    )
  }
}

class DisabledProfileService extends ProfileService(null) {

  override def retrieveProfile(identifier: Long): Profile = {
    Profile(
      identifier,
      "Lia Martins",
      "Observability",
      false
    )
  }
}

class ProfileControllerTest extends AnyFlatSpec with Matchers {

  "ProfileController" should "return active profile" in {

    val controller =
      new ProfileController(
        new FakeProfileService
      )

    val result = controller.findProfile(7L)

    result.id shouldBe 7L
    result.owner shouldBe "Pedro Henrique"
    result.active shouldBe true
  }

  it should "return disabled profile" in {

    val controller =
      new ProfileController(
        new DisabledProfileService
      )

    val result = controller.findProfile(88L)

    result.owner shouldBe "Lia Martins"
    result.active shouldBe false
  }
}