package pedrohk.injection.repository

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class InMemoryProfileRepositoryTest
  extends AnyFlatSpec
    with Matchers {

  "InMemoryProfileRepository" should "find first profile" in {

    val repository =
      new InMemoryProfileRepository()

    val result =
      repository.findById(
        1L
      )

    result.isDefined shouldBe true

    result.get.name shouldBe
      "Pedro Henrique"
  }

  it should "find second profile" in {

    val repository =
      new InMemoryProfileRepository()

    val result =
      repository.findById(
        2L
      )

    result.get.mentor shouldBe
      "Lia"
  }

  it should "return empty for unknown id" in {

    val repository =
      new InMemoryProfileRepository()

    repository
      .findById(
        999L
      ) shouldBe None
  }

}