package pedrohk.beanscope.service

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import pedrohk.beanscope.model.DeveloperWorkspace

class SingletonWorkspaceServiceTest
  extends AnyFlatSpec
    with Matchers {

  "SingletonWorkspaceService" should "return workspace id" in {

    val service =
      new SingletonWorkspaceService(
        new DeveloperWorkspace(
          "Pedro Henrique",
          500L
        )
      )

    service.workspaceId() shouldBe
      "Pedro Henrique-500"
  }

  it should "return owner" in {

    val service =
      new SingletonWorkspaceService(
        new DeveloperWorkspace(
          "Pedro Henrique",
          1L
        )
      )

    service.owner() shouldBe
      "Pedro Henrique"
  }

}