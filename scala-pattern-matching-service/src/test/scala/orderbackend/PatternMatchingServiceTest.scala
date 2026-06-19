package orderbackend

import orderbackend.domain.{Customer, Order, OrderStatus, Payment}
import orderbackend.service.PatternMatchingService
import org.scalatest.funsuite.AnyFunSuite


class PatternMatchingServiceTest
  extends AnyFunSuite:

  val service =
    PatternMatchingService()

  test("vip"):
    val order =
      Order(
        "1",
        Customer(
          "1",
          "john",
          true
        ),
        List("a"),
        50,
        Payment.Pix,
        OrderStatus.Created
      )

    assert(
      service.classify(order) ==
        "VIP"
    )

  test("enterprise"):
    val order =
      Order(
        "2",
        Customer(
          "1",
          "john",
          false
        ),
        List("a"),
        6000,
        Payment.CreditCard,
        OrderStatus.Created
      )

    assert(
      service.classify(order) ==
        "ENTERPRISE"
    )

  test("tuple"):
    assert(
      service.tupleMatch(
        ("admin", 8)
      ) == "root"
    )

  test("typed"):
    assert(
      service.typedMatch(
        5
      ) == "50"
    )

  test("option"):
    assert(
      service.optionMatch(
        None
      ) ==
        "missing"
    )