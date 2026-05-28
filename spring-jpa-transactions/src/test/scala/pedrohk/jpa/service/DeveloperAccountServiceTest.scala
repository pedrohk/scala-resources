package pedrohk.jpa.service

import org.mockito.ArgumentMatchers.any
import java.util.Optional
import org.mockito.Mockito.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import pedrohk.jpa.model.DeveloperAccount
import pedrohk.jpa.repository.DeveloperAccountRepository

class DeveloperAccountServiceTest
  extends AnyFlatSpec
    with Matchers {

  "DeveloperAccountService" should "create account successfully" in {

    val repository =
      mock(classOf[DeveloperAccountRepository])

    val account =
      new DeveloperAccount(
        "Pedro Henrique",
        "Spring",
        80
      )

    when(
      repository.save(any(classOf[DeveloperAccount]))
    ).thenReturn(account)

    val service =
      new DeveloperAccountService(repository)

    val result =
      service.createAccount(
        "Pedro Henrique",
        "Spring",
        80
      )

    result.owner shouldBe "Pedro Henrique"
    result.stack shouldBe "Spring"
    result.credits shouldBe 80
  }

  it should "transfer credits correctly" in {

    val repository =
      mock(classOf[DeveloperAccountRepository])

    val source =
      new DeveloperAccount(
        "Pedro Henrique",
        "Scala",
        100
      )

    val destination =
      new DeveloperAccount(
        "Lia Martins",
        "JPA",
        20
      )

    when(
      repository.save(any(classOf[DeveloperAccount]))
    ).thenAnswer(invocation =>
      invocation.getArgument(0)
    )

    val service =
      new DeveloperAccountService(repository)

    service.transferCredits(
      source,
      destination,
      30
    )

    source.credits shouldBe 70
    destination.credits shouldBe 50

    verify(repository, times(2))
      .save(any(classOf[DeveloperAccount]))
  }

  it should "reject invalid transfers" in {

    val repository =
      mock(classOf[DeveloperAccountRepository])

    val source =
      new DeveloperAccount(
        "Pedro Henrique",
        "Hibernate",
        5
      )

    val destination =
      new DeveloperAccount(
        "Lia Martins",
        "Transactions",
        40
      )

    val service =
      new DeveloperAccountService(repository)

    assertThrows[IllegalArgumentException] {
      service.transferCredits(
        source,
        destination,
        20
      )
    }
  }

  it should "find account by owner" in {

    val repository =
      mock(classOf[DeveloperAccountRepository])

    val account =
      new DeveloperAccount(
        "Pedro Henrique",
        "Spring Data JPA",
        90
      )

    when(
      repository.findByOwner(
        "Pedro Henrique"
      )
    ).thenReturn(
      Optional.of(account)
    )

    val service =
      new DeveloperAccountService(repository)

    val result =
      service.findByOwner(
        "Pedro Henrique"
      )

    result.isDefined shouldBe true
    result.get.owner shouldBe "Pedro Henrique"
  }
}