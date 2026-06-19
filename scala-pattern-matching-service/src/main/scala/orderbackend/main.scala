package orderbackend

import orderbackend.domain.{Customer, Order, OrderStatus, Payment}
import orderbackend.repository.InMemoryOrderRepository
import orderbackend.service.ForComprehensionService


object Main extends App:

  val repository =
    InMemoryOrderRepository(
      List(
        Order(
          "1",
          Customer(
            "c1",
            "Pedro",
            true
          ),
          List(
            "Keyboard"
          ),
          3000,
          Payment.Pix,
          OrderStatus.Paid
        )
      )
    )

  val service =
    ForComprehensionService(
      repository
    )

  println(
    service.usingFor("1")
  )

  println(
    service.usingFlatMap("1")
  )