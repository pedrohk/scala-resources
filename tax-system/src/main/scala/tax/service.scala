package tax

class TaxService(repo: TaxRepository):

  def calculateTax(product: Product, state: State, year: Int, price: BigDecimal): BigDecimal =
    val rate = repo.findRate(product, state, year)
      .getOrElse(throw new IllegalArgumentException("Tax rule not found"))
    (price * rate).setScale(2, BigDecimal.RoundingMode.HALF_UP)

  def calculateTotal(product: Product, state: State, year: Int, price: BigDecimal): BigDecimal =
    val tax = calculateTax(product, state, year, price)
    (price + tax).setScale(2, BigDecimal.RoundingMode.HALF_UP)