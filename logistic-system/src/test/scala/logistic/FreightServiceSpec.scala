package logistic

import org.scalatest.funsuite.AnyFunSuite

class FreightServiceSpec extends AnyFunSuite {

  val rules = List(
    PricingRule(TransportType.TRUCK, 2024, 10, 2, 0.5),
    PricingRule(TransportType.SHIP, 2024, 20, 1, 0.2),
    PricingRule(TransportType.RAIL, 2024, 15, 1.5, 0.3),
    PricingRule(TransportType.TRUCK, 2025, 12, 2.5, 0.6)
  )

  val repo = new InMemoryPricingRepository(rules)
  val service = new FreightService(repo)

  test("truck pricing 2024") {
    val shipment = Shipment(10, Dimensions(2, 2, 2), TransportType.TRUCK)
    val result = service.calculate(shipment, 2024)
    assert(result == 10 + (10 * 2) + (8 * 0.5))
  }

  test("ship pricing 2024") {
    val shipment = Shipment(10, Dimensions(2, 2, 2), TransportType.SHIP)
    val result = service.calculate(shipment, 2024)
    assert(result == 20 + (10 * 1) + (8 * 0.2))
  }

  test("rail pricing 2024") {
    val shipment = Shipment(5, Dimensions(1, 1, 1), TransportType.RAIL)
    val result = service.calculate(shipment, 2024)
    assert(result == 15 + (5 * 1.5) + (1 * 0.3))
  }

  test("different year changes price") {
    val shipment = Shipment(10, Dimensions(2, 2, 2), TransportType.TRUCK)
    val result2024 = service.calculate(shipment, 2024)
    val result2025 = service.calculate(shipment, 2025)
    assert(result2024 != result2025)
  }

  test("rounding works") {
    val customRules = List(
      PricingRule(TransportType.TRUCK, 2024, 0, 0, 0.3333)
    )
    val customRepo = new InMemoryPricingRepository(customRules)
    val customService = new FreightService(customRepo)

    val shipment = Shipment(0, Dimensions(1, 1, 1), TransportType.TRUCK)
    val result = customService.calculate(shipment, 2024)
    assert(result == 0.33)
  }

  test("zero values") {
    val shipment = Shipment(0, Dimensions(0, 0, 0), TransportType.TRUCK)
    val result = service.calculate(shipment, 2024)
    assert(result == 10.00)
  }

  test("large shipment") {
    val shipment = Shipment(10000, Dimensions(100, 100, 100), TransportType.TRUCK)
    val result = service.calculate(shipment, 2024)
    assert(result > 0)
  }

  test("missing rule throws") {
    val shipment = Shipment(10, Dimensions(1, 1, 1), TransportType.SHIP)
    assertThrows[IllegalArgumentException] {
      service.calculate(shipment, 2025)
    }
  }

  test("volume calculation correctness") {
    val dims = Dimensions(2, 3, 4)
    assert(dims.volume == 24)
  }

  test("consistency multiple runs") {
    val shipment = Shipment(10, Dimensions(2, 2, 2), TransportType.TRUCK)
    val r1 = service.calculate(shipment, 2024)
    val r2 = service.calculate(shipment, 2024)
    assert(r1 == r2)
  }
}