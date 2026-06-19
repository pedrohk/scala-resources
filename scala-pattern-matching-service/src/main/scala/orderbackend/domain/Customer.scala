package orderbackend.domain

final case class Customer(
                           id: String,
                           name: String,
                           vip: Boolean
)