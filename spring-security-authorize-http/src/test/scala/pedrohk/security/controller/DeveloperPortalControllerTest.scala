package pedrohk.security.controller

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import pedrohk.security.service.DeveloperProfileService

class DeveloperPortalControllerTest
  extends AnyFlatSpec
    with Matchers {

  private val controller =
    new DeveloperPortalController(
      new DeveloperProfileService
    )

  "status" should "return authorization message" in {

    val response =
      controller.status()

    response.getStatusCode.value() shouldBe 200

    response.getBody shouldBe
      "Authorization configured"
  }

  "profile" should "return Pedro Henrique profile" in {

    val response =
      controller.profile()

    response.getStatusCode.value() shouldBe 200

    response.getBody.owner shouldBe
      "Pedro Henrique"

    response.getBody.specialization shouldBe
      "Spring Security"

    response.getBody.activeProjects shouldBe 8
  }

  "liaProfile" should "return Lia profile" in {

    val response =
      controller.liaProfile()

    response.getStatusCode.value() shouldBe 200

    response.getBody.owner shouldBe
      "Lia"

    response.getBody.specialization shouldBe
      "Platform Governance"

    response.getBody.activeProjects shouldBe 4
  }
}