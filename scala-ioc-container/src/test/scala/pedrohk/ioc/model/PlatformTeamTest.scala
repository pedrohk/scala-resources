package pedrohk.ioc.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PlatformTeamTest
  extends AnyFlatSpec
    with Matchers {

  "PlatformTeam" should "store team name" in {
    val team =
      new PlatformTeam(
        "Lia Platform Team"
      )

    team.teamName shouldBe
      "Lia Platform Team"
  }

}