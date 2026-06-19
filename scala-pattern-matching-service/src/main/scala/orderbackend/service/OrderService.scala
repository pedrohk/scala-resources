package orderbackend.service

import orderbackend.repository.OrderRepository

class OrderService(
                    repository: OrderRepository,
                    flow: ForComprehensionService
                  ):

  def load(id: String): Either[String, String] =
    flow.usingFor(id)