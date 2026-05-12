package bankledger

case class Account(
                    id: String,
                    owner: String,
                    balance: BigDecimal
                  ) {

  def deposit(amount: BigDecimal): Account = {
    validateAmount(amount)

    copy(balance = balance + amount)
  }

  def withdraw(amount: BigDecimal): Account = {
    validateAmount(amount)

    if (amount > balance) {
      throw new IllegalArgumentException("Insufficient balance")
    }

    copy(balance = balance - amount)
  }

  private def validateAmount(amount: BigDecimal): Unit = {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be positive")
    }
  }
}