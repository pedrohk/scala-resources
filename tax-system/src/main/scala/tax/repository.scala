package tax

trait TaxRepository:
  def findRate(product: Product, state: State, year: Int): Option[BigDecimal]

class InMemoryTaxRepository(rules: List[TaxRule]) extends TaxRepository:

  private val index: Map[(String, String, Int), BigDecimal] =
    rules.map(r => ((r.productCategory, r.state.code, r.year), r.rate)).toMap

  override def findRate(product: Product, state: State, year: Int): Option[BigDecimal] =
    index.get((product.category, state.code, year))