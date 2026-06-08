package pedrohk.springvalue.service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ProfileSummaryServiceTest
  extends AnyFunSuite
    with Matchers {

  private def service() =
    new ProfileSummaryService(
      new ProfileValueService(
        "Pedro Henrique",
        "Platform",
        "Lia",
        "production"
      )
    )

  test(
    "shouldGenerateSummaryFromProfileSummaryService"
  ) {

    val result =
      service()
        .createSummary()

    result.summary shouldBe
      "Pedro Henrique works in Platform with Lia (production)"
  }

  test(
    "shouldReturnSummaryTextFromProfileSummaryService"
  ) {

    service()
      .summaryText() shouldBe
      "Pedro Henrique works in Platform with Lia (production)"
  }

}