package orderbackend.repository

import orderbackend.domain.Order

trait OrderRepository:

  def find(id: String): Option[Order]

class InMemoryOrderRepository(
                               orders: List[Order]
                             ) extends OrderRepository:

  override def find(id: String): Option[Order] =
    orders.find(_.id == id)