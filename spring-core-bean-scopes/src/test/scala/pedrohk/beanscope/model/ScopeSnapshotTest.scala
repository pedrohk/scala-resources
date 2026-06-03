package pedrohk.beanscope.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ScopeSnapshotTest
  extends AnyFlatSpec
    with Matchers {

  "ScopeSnapshot" should "store snapshot values" in {

    val snapshot =
      new ScopeSnapshot(
        "singleton",
        "prototype"
      )

    snapshot.singletonId shouldBe
      "singleton"

    snapshot.prototypeId shouldBe
      "prototype"
  }

}