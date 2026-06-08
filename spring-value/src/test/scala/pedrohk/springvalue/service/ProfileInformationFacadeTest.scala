package pedrohk.springvalue.service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ProfileInformationFacadeTest
  extends AnyFunSuite
    with Matchers {

  test(
    "shouldGenerateCompleteDescriptionFromProfileInformationFacade"
  ) {

    val values =
      new ProfileValueService(
        "Pedro Henrique",
        "Platform",
        "Lia",
        "production"
      )

    val summaries =
      new ProfileSummaryService(
        values
      )

    val facade =
      new ProfileInformationFacade(
        values,
        summaries
      )

    facade.profileDescription() shouldBe
      "Pedro Henrique|Platform|Lia|production::Pedro Henrique works in Platform with Lia (production)"
  }

}