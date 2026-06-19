package orderbackend.service

import orderbackend.domain.{Customer, Order, OrderStatus, Payment}

object HighValue:
  def unapply(order: Order): Option[BigDecimal] =
    Option.when(order.amount >= 1000)(order.amount)

class PatternMatchingService:

  def classify(order: Order): String =
    order match
      case Order(_, Customer(_, _, true), _, _, _, _) =>
        "VIP"

      case HighValue(v) if v >= 5000 =>
        "ENTERPRISE"

      case Order(_, _, Nil, _, _, _) =>
        "EMPTY"

      case Order(_, _, item :: Nil, _, _, _) =>
        s"SINGLE:$item"

      case Order(_, _, _, _, Payment.Pix, OrderStatus.Paid) =>
        "PIX"

      case Order(_, _, _, _, _, OrderStatus.Delivered) =>
        "DONE"

      case _ =>
        "STANDARD"

  def paymentDescription(payment: Payment): String =
    payment match
      case Payment.Pix => "instant"
      case Payment.CreditCard => "credit"
      case Payment.DebitCard => "debit"
      case Payment.Cash => "cash"

  def tupleMatch(input: (String, Int)): String =
    input match
      case ("admin", level) if level > 5 =>
        "root"

      case ("user", _) =>
        "normal"

      case _ =>
        "unknown"

  def typedMatch(value: Any): String =
    value match
      case s: String =>
        s.toUpperCase

      case i: Int =>
        (i * 10).toString

      case _ =>
        "unsupported"

  def optionMatch(value: Option[String]): String =
    value match
      case Some(v) =>
        v

      case None =>
        "missing"