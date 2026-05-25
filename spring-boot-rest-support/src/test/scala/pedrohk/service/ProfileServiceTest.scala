package pedrohk.service

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import pedrohk.model.{Profile, ProfileResponse}

class ProfileServiceTest extends AnyFlatSpec with Matchers {

  class FakeProfileGateway extends ProfileGateway(
    null,
    ""
  ) {

    override def fetchProfile(identifier: Long): ProfileResponse = {
      ProfileResponse(
        identifier,
        "Pedro Henrique",
        "Distributed Systems",
        true
      )
    }
  }

  class DisabledProfileGateway extends ProfileGateway(
    null,
    ""
  ) {

    override def fetchProfile(identifier: Long): ProfileResponse = {
      ProfileResponse(
        identifier,
        "Lia Martins",
        "Cloud Infrastructure",
        false
      )
    }
  }

  "ProfileService" should "map gateway response into domain profile" in {

    val gateway = new FakeProfileGateway

    val service = new ProfileService(gateway)

    val result = service.retrieveProfile(15L)

    result shouldBe Profile(
      15L,
      "Pedro Henrique",
      "Distributed Systems",
      true
    )
  }

  it should "preserve disabled profile state" in {

    val gateway = new DisabledProfileGateway

    val service = new ProfileService(gateway)

    val result = service.retrieveProfile(99L)

    result.active shouldBe false
    result.owner shouldBe "Lia Martins"
  }
}