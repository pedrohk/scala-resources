package bankledger

import org.scalatest.funsuite.AnyFunSuite

class LedgerServiceSpec extends AnyFunSuite {

  private def buildService(): LedgerService = {
    new LedgerService(
      new InMemoryAccountRepository(),
      new InMemoryTransactionRepository()
    )
  }

  test("create account starts with zero balance") {
    val service = buildService()

    val account =
      service.createAccount(
        "1",
        "Pedro"
      )

    assert(account.balance == BigDecimal(0))
  }

  test("deposit increases balance") {
    val service = buildService()

    service.createAccount("1", "Pedro")

    val updated =
      service.deposit(
        "1",
        BigDecimal(100)
      )

    assert(updated.balance == BigDecimal(100))
  }

  test("withdraw decreases balance") {
    val service = buildService()

    service.createAccount("1", "Pedro")

    service.deposit("1", BigDecimal(200))

    val updated =
      service.withdraw(
        "1",
        BigDecimal(50)
      )

    assert(updated.balance == BigDecimal(150))
  }

  test("withdraw with insufficient balance throws") {
    val service = buildService()

    service.createAccount("1", "Pedro")

    assertThrows[IllegalArgumentException] {
      service.withdraw(
        "1",
        BigDecimal(500)
      )
    }
  }

  test("negative deposit throws") {
    val service = buildService()

    service.createAccount("1", "Pedro")

    assertThrows[IllegalArgumentException] {
      service.deposit(
        "1",
        BigDecimal(-10)
      )
    }
  }

  test("zero deposit throws") {
    val service = buildService()

    service.createAccount("1", "Pedro")

    assertThrows[IllegalArgumentException] {
      service.deposit(
        "1",
        BigDecimal(0)
      )
    }
  }

  test("transfer moves money between accounts") {
    val service = buildService()

    service.createAccount("1", "Alice")
    service.createAccount("2", "Bob")

    service.deposit("1", BigDecimal(300))

    service.transfer(
      "1",
      "2",
      BigDecimal(100)
    )

    assert(service.balance("1") == BigDecimal(200))
    assert(service.balance("2") == BigDecimal(100))
  }

  test("transfer to same account throws") {
    val service = buildService()

    service.createAccount("1", "Alice")

    service.deposit("1", BigDecimal(100))

    assertThrows[IllegalArgumentException] {
      service.transfer(
        "1",
        "1",
        BigDecimal(50)
      )
    }
  }

  test("transfer with insufficient balance throws") {
    val service = buildService()

    service.createAccount("1", "Alice")
    service.createAccount("2", "Bob")

    assertThrows[IllegalArgumentException] {
      service.transfer(
        "1",
        "2",
        BigDecimal(999)
      )
    }
  }

  test("ledger stores deposit transaction") {
    val service = buildService()

    service.createAccount("1", "Pedro")

    service.deposit(
      "1",
      BigDecimal(100)
    )

    val ledger =
      service.ledger("1")

    assert(ledger.size == 1)
    assert(
      ledger.head.transactionType ==
        TransactionType.DEPOSIT
    )
  }

  test("ledger stores withdraw transaction") {
    val service = buildService()

    service.createAccount("1", "Pedro")

    service.deposit("1", BigDecimal(100))

    service.withdraw(
      "1",
      BigDecimal(40)
    )

    val ledger =
      service.ledger("1")

    assert(ledger.size == 2)

    assert(
      ledger.last.transactionType ==
        TransactionType.WITHDRAW
    )
  }

  test("transfer creates two ledger entries") {
    val service = buildService()

    service.createAccount("1", "Alice")
    service.createAccount("2", "Bob")

    service.deposit("1", BigDecimal(500))

    service.transfer(
      "1",
      "2",
      BigDecimal(200)
    )

    val sourceLedger =
      service.ledger("1")

    val targetLedger =
      service.ledger("2")

    assert(
      sourceLedger.exists(
        _.transactionType ==
          TransactionType.TRANSFER_OUT
      )
    )

    assert(
      targetLedger.exists(
        _.transactionType ==
          TransactionType.TRANSFER_IN
      )
    )
  }

  test("account not found throws") {
    val service = buildService()

    assertThrows[IllegalArgumentException] {
      service.balance("missing")
    }
  }

  test("accounts returns all created accounts") {
    val service = buildService()

    service.createAccount("1", "Alice")
    service.createAccount("2", "Bob")

    val accounts =
      service.accounts()

    assert(accounts.size == 2)
  }

  test("transactions contain updated balance") {
    val service = buildService()

    service.createAccount("1", "Pedro")

    service.deposit("1", BigDecimal(100))

    val tx =
      service.ledger("1").head

    assert(tx.balanceAfter == BigDecimal(100))
  }

  test("multiple deposits accumulate balance") {
    val service = buildService()

    service.createAccount("1", "Pedro")

    service.deposit("1", BigDecimal(10))
    service.deposit("1", BigDecimal(20))
    service.deposit("1", BigDecimal(30))

    assert(
      service.balance("1") == BigDecimal(60)
    )
  }

  test("multiple withdrawals accumulate correctly") {
    val service = buildService()

    service.createAccount("1", "Pedro")

    service.deposit("1", BigDecimal(500))

    service.withdraw("1", BigDecimal(100))
    service.withdraw("1", BigDecimal(50))

    assert(
      service.balance("1") == BigDecimal(350)
    )
  }

  test("ledger remains deterministic") {
    val service = buildService()

    service.createAccount("1", "Pedro")

    service.deposit("1", BigDecimal(100))

    val first =
      service.ledger("1").size

    val second =
      service.ledger("1").size

    assert(first == second)
  }

  test("transaction ids are generated") {
    val service = buildService()

    service.createAccount("1", "Pedro")

    service.deposit("1", BigDecimal(100))

    val tx =
      service.ledger("1").head

    assert(tx.id.nonEmpty)
  }

  test("large balances work correctly") {
    val service = buildService()

    service.createAccount("1", "Enterprise")

    service.deposit(
      "1",
      BigDecimal("9999999999")
    )

    assert(
      service.balance("1") ==
        BigDecimal("9999999999")
    )
  }
}