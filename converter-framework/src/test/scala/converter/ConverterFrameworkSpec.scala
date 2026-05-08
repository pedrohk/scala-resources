package converter

import org.scalatest.funsuite.AnyFunSuite

class ConverterFrameworkSpec extends AnyFunSuite {

  val addressConverter = new AddressConverter
  val userConverter = new UserConverter(addressConverter)
  val productConverter = new ProductConverter
  val orderConverter =
    new OrderConverter(userConverter, productConverter)

  def buildUser(): User = {
    User(
      id = "u1",
      firstName = "John",
      lastName = "Doe",
      age = 30,
      address = Address(
        "Main Street",
        "New York",
        "USA",
        "10001"
      )
    )
  }

  def buildProduct(): Product = {
    Product(
      id = "p1",
      name = "Laptop",
      price = 1000.50,
      quantity = 2
    )
  }

  test("convert address") {

    val address = Address(
      "Main",
      "NY",
      "USA",
      "10001"
    )

    val dto = addressConverter.convert(address)

    assert(dto.fullAddress.contains("Main"))
    assert(dto.fullAddress.contains("NY"))
  }

  test("convert user") {

    val user = buildUser()

    val dto = userConverter.convert(user)

    assert(dto.id == "u1")
    assert(dto.fullName == "John Doe")
    assert(dto.address.fullAddress.contains("USA"))
  }

  test("convert product") {

    val product = buildProduct()

    val dto = productConverter.convert(product)

    assert(dto.id == "p1")
    assert(dto.displayName == "LAPTOP")
    assert(dto.inventoryValue == 2001.00)
  }

  test("convert order") {

    val order = Order(
      id = "o1",
      user = buildUser(),
      products = List(
        buildProduct()
      )
    )

    val dto = orderConverter.convert(order)

    assert(dto.id == "o1")
    assert(dto.customer.fullName == "John Doe")
    assert(dto.products.size == 1)
    assert(dto.total == 2001.00)
  }

  test("registry registration") {

    val registry = new ConverterRegistry

    registry.register(
      classOf[Address],
      classOf[AddressDTO],
      addressConverter
    )

    assert(registry.size() == 1)
  }

  test("registry conversion") {

    val registry = new ConverterRegistry

    registry.register(
      classOf[Address],
      classOf[AddressDTO],
      addressConverter
    )

    val result = registry.convert(
      Address(
        "Street",
        "City",
        "Country",
        "000"
      ),
      classOf[Address],
      classOf[AddressDTO]
    )

    assert(result.fullAddress.contains("Street"))
  }

  test("missing converter throws") {

    val registry = new ConverterRegistry

    assertThrows[IllegalArgumentException] {

      registry.convert(
        buildUser(),
        classOf[User],
        classOf[UserDTO]
      )
    }
  }

  test("multiple products total calculation") {

    val order = Order(
      id = "o2",
      user = buildUser(),
      products = List(
        Product("1", "Mouse", 10.25, 2),
        Product("2", "Keyboard", 50.10, 1)
      )
    )

    val dto = orderConverter.convert(order)

    assert(dto.total == 70.60)
  }

  test("empty product list") {

    val order = Order(
      id = "o3",
      user = buildUser(),
      products = List.empty
    )

    val dto = orderConverter.convert(order)

    assert(dto.products.isEmpty)
    assert(dto.total == 0.00)
  }

  test("rounding precision") {

    val product = Product(
      "p2",
      "Cable",
      10.333,
      3
    )

    val dto = productConverter.convert(product)

    assert(dto.inventoryValue == 31.00)
  }

  test("high volume conversion") {

    val products =
      (1 to 1000).map { i =>
        Product(
          s"p$i",
          s"product-$i",
          i.toDouble,
          1
        )
      }.toList

    val converted =
      products.map(productConverter.convert)

    assert(converted.size == 1000)
    assert(converted.head.displayName == "PRODUCT-1")
  }

  test("converter registry multiple registrations") {

    val registry = new ConverterRegistry

    registry.register(
      classOf[Address],
      classOf[AddressDTO],
      addressConverter
    )

    registry.register(
      classOf[Product],
      classOf[ProductDTO],
      productConverter
    )

    registry.register(
      classOf[User],
      classOf[UserDTO],
      userConverter
    )

    assert(registry.size() == 3)
  }

  test("order conversion preserves product order") {

    val order = Order(
      id = "o4",
      user = buildUser(),
      products = List(
        Product("1", "First", 1, 1),
        Product("2", "Second", 1, 1)
      )
    )

    val dto = orderConverter.convert(order)

    assert(dto.products.head.displayName == "FIRST")
    assert(dto.products.last.displayName == "SECOND")
  }

  test("address formatting correctness") {

    val address = Address(
      "A",
      "B",
      "C",
      "D"
    )

    val dto = addressConverter.convert(address)

    assert(dto.fullAddress == "A, B, C, D")
  }

  test("user age preserved") {

    val dto = userConverter.convert(buildUser())

    assert(dto.age == 30)
  }

  test("large order conversion") {

    val products =
      (1 to 500).map { i =>
        Product(
          s"id-$i",
          s"name-$i",
          5.0,
          2
        )
      }.toList

    val order = Order(
      id = "large",
      user = buildUser(),
      products = products
    )

    val dto = orderConverter.convert(order)

    assert(dto.products.size == 500)
    assert(dto.total == 5000.00)
  }
}