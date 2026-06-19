package orderbackend.domain

sealed trait OrderStatus

object OrderStatus:
  case object Created extends OrderStatus
  case object Paid extends OrderStatus
  case object Delivered extends OrderStatus
  case object Cancelled extends OrderStatus

final case class Order(
                        id: String,
                        customer: Customer,
                        items: List[String],
                        amount: BigDecimal,
                        payment: Payment,
                        status: OrderStatus
                      )