package pedrohk.security.service

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import pedrohk.security.model.DeveloperProfile

class DeveloperProfileServiceTest
  extends AnyFlatSpec
    with Matchers {

  private val service =
    new DeveloperProfileService

  "loadPedroHenriqueProfile" should "return Pedro Henrique profile" in {

    val profile =
      service.loadPedroHenriqueProfile()

    profile.owner shouldBe
      "Pedro Henrique"

    profile.specialization shouldBe
      "Spring Security"

    profile.activeProjects shouldBe 8
  }

  "loadLiaProfile" should "return Lia profile" in {

    val profile =
      service.loadLiaProfile()

    profile.owner shouldBe
      "Lia"

    profile.specialization shouldBe
      "Platform Governance"

    profile.activeProjects shouldBe 4
  }

  "validateAccess" should "return true for valid profile" in {

    val profile =
      new DeveloperProfile(
        "Pedro Henrique",
        "Security",
        1
      )

    service.validateAccess(profile) shouldBe true
  }

  it should "return false for null profile" in {

    service.validateAccess(null) shouldBe false
  }

  it should "return false for empty owner" in {

    val profile =
      new DeveloperProfile(
        "",
        "Security",
        1
      )

    service.validateAccess(profile) shouldBe false
  }

  it should "return false for negative project count" in {

    val profile =
      new DeveloperProfile(
        "Pedro Henrique",
        "Security",
        -1
      )

    service.validateAccess(profile) shouldBe false
  }

  "totalProjects" should "sum project counts" in {

    val first =
      new DeveloperProfile(
        "Pedro Henrique",
        "Security",
        8
      )

    val second =
      new DeveloperProfile(
        "Lia",
        "Governance",
        4
      )

    service.totalProjects(
      first,
      second
    ) shouldBe 12
  }

  it should "support zero values" in {

    val first =
      new DeveloperProfile(
        "Pedro Henrique",
        "Security",
        0
      )

    val second =
      new DeveloperProfile(
        "Lia",
        "Governance",
        0
      )

    service.totalProjects(
      first,
      second
    ) shouldBe 0
  }
}