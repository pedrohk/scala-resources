package bankledger

trait AccountRepository {

  def save(account: Account): Unit

  def findById(id: String): Option[Account]

  def findAll(): List[Account]
}