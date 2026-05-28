package pedrohk.jpa.repository

import java.util.Optional

import org.mockito.Mockito.*

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import pedrohk.jpa.model.DeveloperAccount

class DeveloperAccountRepositoryTest
  extends AnyFlatSpec
    with Matchers {

  "DeveloperAccountRepository" should "save account" in {

    val repository =
      mock(classOf[DeveloperAccountRepository])

    val account =
      new DeveloperAccount(
        "Pedro Henrique",
        "JPA",
        70
      )

    when(
      repository.save(account)
    ).thenReturn(account)

    val result =
      repository.save(account)

    result.owner shouldBe "Pedro Henrique"
  }

  it should "find account by owner" in {

    val repository =
      mock(classOf[DeveloperAccountRepository])

    val account =
      new DeveloperAccount(
        "Lia Martins",
        "Transactions",
        60
      )

    when(
      repository.findByOwner(
        "Lia Martins"
      )
    ).thenReturn(
      Optional.of(account)
    )

    val result =
      repository.findByOwner(
        "Lia Martins"
      )

    result.isPresent shouldBe true
    result.get.owner shouldBe "Lia Martins"
  }
}