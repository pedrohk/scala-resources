package pedrohk.ioc.service

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import pedrohk.ioc.model.PlatformTeam

class TeamServiceTest
  extends AnyFlatSpec
    with Matchers {

  "TeamService" should "return team label" in {
    val service =
      new TeamService(
        new PlatformTeam(
          "Lia Platform Team"
        )
      )

    service.teamLabel() shouldBe
      "Lia Platform Team"
  }

}