package bankledger

trait TransactionRepository {

  def save(transaction: Transaction): Unit

  def findByAccountId(accountId: String): List[Transaction]

  def findAll(): List[Transaction]
}