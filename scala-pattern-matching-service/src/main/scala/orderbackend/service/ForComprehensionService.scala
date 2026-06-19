package orderbackend.service

import orderbackend.domain.Order
import orderbackend.repository.OrderRepository

class ForComprehensionService(
                               repository: OrderRepository
                             ):

  def usingFor(id: String): Either[String, String] =
    for
      order <- repository.find(id).toRight("not-found")
      validated <- Either.cond(
        order.amount > 0,
        order,
        "invalid"
      )
    yield s"${order.id}:${order.amount}"

  def usingFlatMap(id: String): Either[String, String] =
    repository
      .find(id)
      .toRight("not-found")
      .flatMap { order =>
        Either.cond(
          order.amount > 0,
          s"${order.id}:${order.amount}",
          "invalid"
        )
      }