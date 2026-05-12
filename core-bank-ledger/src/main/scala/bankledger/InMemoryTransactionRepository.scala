package bankledger

import scala.collection.mutable

class InMemoryTransactionRepository extends TransactionRepository {

  private val transactions =
    mutable.ListBuffer.empty[Transaction]

  override def save(transaction: Transaction): Unit = {
    transactions += transaction
  }

  override def findByAccountId(accountId: String): List[Transaction] = {
    transactions
      .filter(_.accountId == accountId)
      .toList
  }

  override def findAll(): List[Transaction] = {
    transactions.toList
  }
}