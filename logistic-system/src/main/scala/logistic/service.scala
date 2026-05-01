package logistic

class FreightService(repo: PricingRepository) {

  def calculate(shipment: Shipment, year: Int): Double = {
    val rule = repo.findRule(shipment.transportType, year)
      .getOrElse(throw new IllegalArgumentException("Pricing rule not found"))

    val volumeCost = shipment.dimensions.volume * rule.volumeFactor
    val weightCost = shipment.weight * rule.weightFactor

    val total = rule.baseRate + volumeCost + weightCost
    BigDecimal(total).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }
}