package orderbackend

import org.scalatest.funsuite.AnyFunSuite
import orderbackend.domain.{Customer, Order, OrderStatus, Payment}
import orderbackend.repository.InMemoryOrderRepository
import orderbackend.service.ForComprehensionService

class ForComprehensionServiceTest
  extends AnyFunSuite:

  val repo =
    InMemoryOrderRepository(
      List(
        Order(
          "1",
          Customer(
            "1",
            "ana",
            false
          ),
          List(
            "book"
          ),
          100,
          Payment.Pix,
          OrderStatus.Paid
        )
      )
    )

  val service =
    ForComprehensionService(
      repo
    )

  test("for comprehension"):
    assert(
      service.usingFor(
        "1"
      ).isRight
    )

  test("flatmap"):
    assert(
      service.usingFlatMap(
        "1"
      ).isRight
    )

  test("not found"):
    assert(
      service.usingFor(
        "999"
      ) ==
        Left("not-found")
    )