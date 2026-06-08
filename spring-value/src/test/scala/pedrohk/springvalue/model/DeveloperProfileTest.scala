package pedrohk.springvalue.model

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class DeveloperProfileTest
  extends AnyFunSuite
    with Matchers {

  test(
    "shouldGenerateProfileKeyFromDeveloperProfile"
  ) {

    val profile =
      new DeveloperProfile(
        "Pedro Henrique",
        "Platform",
        "Lia",
        "production"
      )

    profile.profileKey() shouldBe
      "Pedro Henrique|Platform|Lia|production"
  }

}