package tax

import org.scalatest.funsuite.AnyFunSuite

class TaxServiceSpec extends AnyFunSuite:

  val food = Product("Bread", "food")
  val electronics = Product("Laptop", "electronics")

  val rs = State("RS")
  val sp = State("SP")

  val rules = List(
    TaxRule("food", rs, 2024, BigDecimal(0.05)),
    TaxRule("food", rs, 2025, BigDecimal(0.06)),
    TaxRule("electronics", rs, 2024, BigDecimal(0.20)),
    TaxRule("electronics", sp, 2024, BigDecimal(0.18))
  )

  val repo = new InMemoryTaxRepository(rules)
  val service = new TaxService(repo)

  test("calculate tax for food RS 2024"):
    val tax = service.calculateTax(food, rs, 2024, BigDecimal(100))
    assert(tax == BigDecimal(5.00))

  test("calculate tax for food RS 2025"):
    val tax = service.calculateTax(food, rs, 2025, BigDecimal(100))
    assert(tax == BigDecimal(6.00))

  test("calculate tax for electronics RS 2024"):
    val tax = service.calculateTax(electronics, rs, 2024, BigDecimal(100))
    assert(tax == BigDecimal(20.00))

  test("calculate tax for electronics SP 2024"):
    val tax = service.calculateTax(electronics, sp, 2024, BigDecimal(100))
    assert(tax == BigDecimal(18.00))

  test("calculate total price"):
    val total = service.calculateTotal(electronics, rs, 2024, BigDecimal(100))
    assert(total == BigDecimal(120.00))

  test("missing tax rule throws exception"):
    assertThrows[IllegalArgumentException]:
      service.calculateTax(food, sp, 2024, BigDecimal(100))

  test("precision rounding works"):
    val customRules = List(
      TaxRule("food", rs, 2024, BigDecimal("0.0333"))
    )
    val customRepo = new InMemoryTaxRepository(customRules)
    val customService = new TaxService(customRepo)

    val tax = customService.calculateTax(food, rs, 2024, BigDecimal(100))
    assert(tax == BigDecimal(3.33))

  test("zero price results in zero tax"):
    val tax = service.calculateTax(food, rs, 2024, BigDecimal(0))
    assert(tax == BigDecimal(0.00))

  test("large value calculation"):
    val tax = service.calculateTax(electronics, rs, 2024, BigDecimal(1000000))
    assert(tax == BigDecimal(200000.00))

  test("different year same product different tax"):
    val tax2024 = service.calculateTax(food, rs, 2024, BigDecimal(100))
    val tax2025 = service.calculateTax(food, rs, 2025, BigDecimal(100))
    assert(tax2024 != tax2025)