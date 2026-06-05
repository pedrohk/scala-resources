package pedrohk.beanprocessor

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ApplicationTest
  extends AnyFunSuite
    with Matchers {

  test("ShouldStartApplicationSuccessfully") {

    noException should be thrownBy {

      Application.main(
        Array.empty
      )

    }

  }

}