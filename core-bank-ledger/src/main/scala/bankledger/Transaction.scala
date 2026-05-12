package bankledger

import java.time.Instant

case class Transaction(
                        id: String,
                        accountId: String,
                        transactionType: TransactionType,
                        amount: BigDecimal,
                        balanceAfter: BigDecimal,
                        timestamp: Instant
                      ) {
}