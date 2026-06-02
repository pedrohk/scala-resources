package pedrohk.ioc.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DeveloperProfileTest
  extends AnyFlatSpec
    with Matchers {

  "DeveloperProfile" should "store values" in {
    val profile =
      new DeveloperProfile(
        "Pedro Henrique",
        "Spring Core"
      )

    profile.fullName shouldBe "Pedro Henrique"
    profile.specialization shouldBe "Spring Core"
  }

  it should "generate description" in {
    val profile =
      new DeveloperProfile(
        "Pedro Henrique",
        "Spring Core"
      )

    profile.description shouldBe
      "Pedro Henrique works with Spring Core"
  }

}