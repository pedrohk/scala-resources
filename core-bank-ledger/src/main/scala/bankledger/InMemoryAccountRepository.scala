package bankledger

import scala.collection.mutable

class InMemoryAccountRepository extends AccountRepository {

  private val storage =
    mutable.Map.empty[String, Account]

  override def save(account: Account): Unit = {
    storage.update(account.id, account)
  }

  override def findById(id: String): Option[Account] = {
    storage.get(id)
  }

  override def findAll(): List[Account] = {
    storage.values.toList
  }
}