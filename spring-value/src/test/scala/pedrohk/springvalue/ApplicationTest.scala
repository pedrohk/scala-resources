package pedrohk.springvalue

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class ApplicationTest
  extends AnyFunSuite
    with Matchers {

  test(
    "shouldStartApplication"
  ) {

    noException shouldBe
      thrownBy {

        Application.main(
          Array.empty
        )

      }

  }

}