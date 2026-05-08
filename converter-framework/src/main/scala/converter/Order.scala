package converter

case class Order(
                  id: String,
                  user: User,
                  products: List[Product]
                )