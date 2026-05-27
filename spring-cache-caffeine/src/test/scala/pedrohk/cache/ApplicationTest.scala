package pedrohk.cache

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ApplicationTest
  extends AnyFlatSpec
    with Matchers {

  "Application" should "instantiate successfully" in {

    val application =
      new Application

    application shouldBe a[Application]
  }

  it should "contain executable main object" in {

    Application shouldBe an[Object]
  }
}