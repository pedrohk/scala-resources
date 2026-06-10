package grocerytodo

final case class GroceryItem(
                              name: String,
                              done: Boolean = false
                            ) {

  def markDone: GroceryItem = {
    copy(done = true)
  }

  def redo: GroceryItem = {
    copy(done = false)
  }
}
