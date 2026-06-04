package pedrohk.injection.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DeveloperProfileTest
  extends AnyFlatSpec
    with Matchers {

  "DeveloperProfile" should "store values correctly" in {

    val profile =
      new DeveloperProfile(
        1L,
        "Pedro Henrique",
        "Lia"
      )

    profile.id shouldBe 1L
    profile.name shouldBe "Pedro Henrique"
    profile.mentor shouldBe "Lia"
  }

  it should "generate summary" in {

    val profile =
      new DeveloperProfile(
        3L,
        "Pedro Henrique",
        "Lia"
      )

    profile.summary() shouldBe
      "3:Pedro Henrique:Lia"
  }

}