package pedrohk.security

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ApplicationTest
  extends AnyFlatSpec
    with Matchers {

  "Application" should "be instantiated" in {

    val application =
      new Application

    application should not be null
  }
}