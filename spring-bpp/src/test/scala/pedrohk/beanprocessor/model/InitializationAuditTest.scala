package pedrohk.beanprocessor.model

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class InitializationAuditTest
  extends AnyFunSuite
    with Matchers {

  test("shouldStoreInitializationValuesFromInitializationAudit") {

    val audit =
      new InitializationAudit(
        true,
        "Pedro Henrique"
      )

    audit.initialized shouldBe true
    audit.owner shouldBe "Pedro Henrique"
  }

  test("shouldGenerateDescriptionFromInitializationAudit") {

    val audit =
      new InitializationAudit(
        false,
        "Lia"
      )

    audit.description() shouldBe
      "Lia:false"
  }

}