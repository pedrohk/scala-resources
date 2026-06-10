package grocerytodo

final case class GroceryTodoList(
                                  items: Vector[GroceryItem]
                                ) {

  def addItem(
               name: String
             ): GroceryTodoList = {
    copy(
      items = items :+ GroceryItem(name)
    )
  }

  def removeItem(
                  name: String
                ): GroceryTodoList = {
    copy(
      items = items.filterNot(
        _.name == name
      )
    )
  }

  def markAsDone(
                  name: String
                ): GroceryTodoList = {
    copy(
      items = items.map {
        case item if item.name == name =>
          item.markDone
        case item =>
          item
      }
    )
  }

  def redoItem(
                name: String
              ): GroceryTodoList = {
    copy(
      items = items.map {
        case item if item.name == name =>
          item.redo
        case item =>
          item
      }
    )
  }

  def listAll: Vector[GroceryItem] = {
    items
  }
}

object GroceryTodoList {

  def empty: GroceryTodoList = {
    GroceryTodoList(
      Vector.empty
    )
  }
}