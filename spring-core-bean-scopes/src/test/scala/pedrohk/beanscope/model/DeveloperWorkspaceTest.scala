package pedrohk.beanscope.model

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DeveloperWorkspaceTest
  extends AnyFlatSpec
    with Matchers {

  "DeveloperWorkspace" should "store values correctly" in {

    val workspace =
      new DeveloperWorkspace(
        "Pedro Henrique",
        100L
      )

    workspace.owner shouldBe
      "Pedro Henrique"

    workspace.createdAt shouldBe
      100L
  }

  it should "generate identity" in {

    val workspace =
      new DeveloperWorkspace(
        "Pedro Henrique",
        900L
      )

    workspace.identity shouldBe
      "Pedro Henrique-900"
  }

}