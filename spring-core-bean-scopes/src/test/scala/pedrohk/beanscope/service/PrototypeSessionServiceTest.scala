package pedrohk.beanscope.service

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import pedrohk.beanscope.model.ScopeSnapshot

class PrototypeSessionServiceTest
  extends AnyFlatSpec
    with Matchers {

  "PrototypeSessionService" should "return singleton reference" in {

    val service =
      new PrototypeSessionService(
        new ScopeSnapshot(
          "stable",
          "generated"
        )
      )

    service.singletonReference() shouldBe
      "stable"
  }

  it should "return prototype reference" in {

    val service =
      new PrototypeSessionService(
        new ScopeSnapshot(
          "stable",
          "dynamic"
        )
      )

    service.prototypeReference() shouldBe
      "dynamic"
  }

  it should "create description" in {

    val service =
      new PrototypeSessionService(
        new ScopeSnapshot(
          "workspace",
          "session"
        )
      )

    service.description() shouldBe
      "workspace:session"
  }

}