package logistic

case class PricingRule(
                        transportType: TransportType,
                        year: Int,
                        baseRate: Double,
                        weightFactor: Double,
                        volumeFactor: Double
                      )

trait PricingRepository {
  def findRule(transportType: TransportType, year: Int): Option[PricingRule]
}

class InMemoryPricingRepository(rules: List[PricingRule]) extends PricingRepository {

  private val index: Map[(TransportType, Int), PricingRule] =
    rules.map(r => ((r.transportType, r.year), r)).toMap

  override def findRule(transportType: TransportType, year: Int): Option[PricingRule] = {
    index.get((transportType, year))
  }
}