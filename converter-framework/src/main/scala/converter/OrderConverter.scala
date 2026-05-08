package converter

class OrderConverter(
                      userConverter: Converter[User, UserDTO],
                      productConverter: Converter[Product, ProductDTO]
                    ) extends Converter[Order, OrderDTO] {

  override def convert(value: Order): OrderDTO = {

    val convertedProducts = value.products.map(productConverter.convert)

    val total = BigDecimal(
      value.products.map(p => p.price * p.quantity).sum
    ).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

    OrderDTO(
      id = value.id,
      customer = userConverter.convert(value.user),
      products = convertedProducts,
      total = total
    )
  }
}