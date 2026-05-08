package converter

case class OrderDTO(
                     id: String,
                     customer: UserDTO,
                     products: List[ProductDTO],
                     total: Double
                   )