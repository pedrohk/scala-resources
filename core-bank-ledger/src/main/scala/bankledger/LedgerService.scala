package bankledger

import java.time.Instant
import java.util.UUID

class LedgerService(
                     accountRepository: AccountRepository,
                     transactionRepository: TransactionRepository
                   ) {

  def createAccount(
                     id: String,
                     owner: String
                   ): Account = {
    val account =
      Account(
        id,
        owner,
        BigDecimal(0)
      )

    accountRepository.save(account)

    account
  }

  def deposit(
               accountId: String,
               amount: BigDecimal
             ): Account = {
    val account = getAccount(accountId)

    val updated =
      account.deposit(amount)

    accountRepository.save(updated)

    transactionRepository.save(
      buildTransaction(
        updated,
        TransactionType.DEPOSIT,
        amount
      )
    )

    updated
  }

  def withdraw(
                accountId: String,
                amount: BigDecimal
              ): Account = {
    val account = getAccount(accountId)

    val updated =
      account.withdraw(amount)

    accountRepository.save(updated)

    transactionRepository.save(
      buildTransaction(
        updated,
        TransactionType.WITHDRAW,
        amount
      )
    )

    updated
  }

  def transfer(
                fromAccountId: String,
                toAccountId: String,
                amount: BigDecimal
              ): Unit = {
    if (fromAccountId == toAccountId) {
      throw new IllegalArgumentException("Accounts must differ")
    }

    val source = getAccount(fromAccountId)
    val target = getAccount(toAccountId)

    val updatedSource =
      source.withdraw(amount)

    val updatedTarget =
      target.deposit(amount)

    accountRepository.save(updatedSource)
    accountRepository.save(updatedTarget)

    transactionRepository.save(
      buildTransaction(
        updatedSource,
        TransactionType.TRANSFER_OUT,
        amount
      )
    )

    transactionRepository.save(
      buildTransaction(
        updatedTarget,
        TransactionType.TRANSFER_IN,
        amount
      )
    )
  }

  def balance(accountId: String): BigDecimal = {
    getAccount(accountId).balance
  }

  def ledger(accountId: String): List[Transaction] = {
    transactionRepository.findByAccountId(accountId)
  }

  def accounts(): List[Account] = {
    accountRepository.findAll()
  }

  private def getAccount(id: String): Account = {
    accountRepository
      .findById(id)
      .getOrElse(
        throw new IllegalArgumentException("Account not found")
      )
  }

  private def buildTransaction(
                                account: Account,
                                transactionType: TransactionType,
                                amount: BigDecimal
                              ): Transaction = {
    Transaction(
      UUID.randomUUID().toString,
      account.id,
      transactionType,
      amount,
      account.balance,
      Instant.now()
    )
  }
}