package pedrohk.jpa.controller

import org.mockito.Mockito.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import pedrohk.jpa.model.DeveloperAccount
import pedrohk.jpa.service.DeveloperAccountService

class DeveloperAccountControllerTest
  extends AnyFlatSpec
    with Matchers {

  "DeveloperAccountController" should "return account by owner" in {

    val service =
      mock(classOf[DeveloperAccountService])

    val account =
      new DeveloperAccount(
        "Pedro Henrique",
        "Transactions",
        100
      )

    when(
      service.findByOwner(
        "Pedro Henrique"
      )
    ).thenReturn(
      Some(account)
    )

    val controller =
      new DeveloperAccountController(service)

    val result =
      controller.findAccount(
        "Pedro Henrique"
      )

    result.owner shouldBe "Pedro Henrique"
  }

  it should "return null when account does not exist" in {

    val service =
      mock(classOf[DeveloperAccountService])

    when(
      service.findByOwner(
        "Lia Martins"
      )
    ).thenReturn(None)

    val controller =
      new DeveloperAccountController(service)

    val result =
      controller.findAccount(
        "Lia Martins"
      )

    result shouldBe null
  }
}