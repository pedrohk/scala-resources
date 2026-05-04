package restaurant

case class Dish(name: String, preparationTime: Int)

case class OrderItem(dish: Dish, quantity: Int)

case class Order(items: List[OrderItem])