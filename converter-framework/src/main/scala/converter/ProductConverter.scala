package converter

class ProductConverter extends Converter[Product, ProductDTO] {

  override def convert(value: Product): ProductDTO = {
    ProductDTO(
      id = value.id,
      displayName = value.name.toUpperCase(),
      inventoryValue = BigDecimal(value.price * value.quantity)
        .setScale(2, BigDecimal.RoundingMode.HALF_UP)
        .toDouble
    )
  }
}