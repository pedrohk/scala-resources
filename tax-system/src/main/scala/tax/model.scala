package tax

case class Product(name: String, category: String)

case class State(code: String)

case class TaxRule(
                    productCategory: String,
                    state: State,
                    year: Int,
                    rate: BigDecimal
                  )