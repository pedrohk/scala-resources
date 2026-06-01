package pedrohk.security.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DeveloperProfileTest
  extends AnyFlatSpec
    with Matchers {

  "DeveloperProfile constructor" should "store all values" in {

    val profile =
      new DeveloperProfile(
        "Pedro Henrique",
        "Spring Security",
        8
      )

    profile.owner shouldBe "Pedro Henrique"
    profile.specialization shouldBe "Spring Security"
    profile.activeProjects shouldBe 8
  }

  it should "allow field updates" in {

    val profile =
      new DeveloperProfile(
        "Lia",
        "Architecture",
        2
      )

    profile.specialization =
      "Governance"

    profile.activeProjects =
      5

    profile.specialization shouldBe
      "Governance"

    profile.activeProjects shouldBe
      5
  }

  it should "support default constructor" in {

    val profile =
      new DeveloperProfile

    profile.owner shouldBe ""
    profile.specialization shouldBe ""
    profile.activeProjects shouldBe 0
  }
}