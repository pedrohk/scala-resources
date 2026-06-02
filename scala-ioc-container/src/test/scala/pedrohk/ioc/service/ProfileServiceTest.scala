package pedrohk.ioc.service

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import pedrohk.ioc.repository.InMemoryProfileRepository

class ProfileServiceTest
  extends AnyFlatSpec
    with Matchers {

  "ProfileService" should "load profile name" in {
    val repository =
      new InMemoryProfileRepository()

    val service =
      new ProfileService(repository)

    service.profileName() shouldBe
      "Pedro Henrique"
  }

  it should "create greeting" in {
    val repository =
      new InMemoryProfileRepository()

    val service =
      new ProfileService(repository)

    service.greeting() shouldBe
      "Welcome Pedro Henrique"
  }

}