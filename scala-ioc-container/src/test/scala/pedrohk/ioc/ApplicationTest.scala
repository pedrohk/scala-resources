package pedrohk.ioc

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ApplicationTest
  extends AnyFlatSpec
    with Matchers {

  "Application" should "start successfully" in {
    noException should be thrownBy {
      Application.main(Array.empty)
    }
  }

}