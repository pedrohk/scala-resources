package pedrohk.springvalue.service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ProfileValueServiceTest
  extends AnyFunSuite
    with Matchers {

  private def createService() =
    new ProfileValueService(
      "Pedro Henrique",
      "Platform",
      "Lia",
      "production"
    )

  test(
    "shouldCreateDeveloperProfileFromProfileValueService"
  ) {

    val profile =
      createService()
        .buildProfile()

    profile.owner shouldBe
      "Pedro Henrique"

    profile.team shouldBe
      "Platform"

    profile.mentor shouldBe
      "Lia"

    profile.environment shouldBe
      "production"
  }

  test(
    "shouldExposeValuesFromProfileValueService"
  ) {

    val service =
      createService()

    service.ownerName() shouldBe
      "Pedro Henrique"

    service.teamName() shouldBe
      "Platform"

    service.mentorName() shouldBe
      "Lia"

    service.environmentName() shouldBe
      "production"
  }

}