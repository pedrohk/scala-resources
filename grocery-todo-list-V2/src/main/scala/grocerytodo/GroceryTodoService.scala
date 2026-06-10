package grocerytodo

object GroceryTodoService {

  def add(
           list: GroceryTodoList,
           item: String
         ): Option[GroceryTodoList] = {
    Option.when(
      item.trim.nonEmpty
    ) {
      list.addItem(item)
    }
  }

  def remove(
              list: GroceryTodoList,
              item: String
            ): Option[GroceryTodoList] = {
    Option(
      list.removeItem(item)
    )
  }

  def markDone(
                list: GroceryTodoList,
                item: String
              ): Option[GroceryTodoList] = {
    Option.when(
      list.items.exists(
        _.name == item
      )
    ) {
      list.markAsDone(item)
    }
  }

  def redo(
            list: GroceryTodoList,
            item: String
          ): Option[GroceryTodoList] = {
    Option.when(
      list.items.exists(
        _.name == item
      )
    ) {
      list.redoItem(item)
    }
  }

  def workflow(): Option[GroceryTodoList] = {
    for {
      initial <- Option(
        GroceryTodoList.empty
      )

      first <- add(
        initial,
        "coffee"
      )

      second <- add(
        first,
        "olive oil"
      )

      third <- add(
        second,
        "pear"
      )

      fourth <- markDone(
        third,
        "olive oil"
      )

      fifth <- redo(
        fourth,
        "olive oil"
      )

      finalList <- remove(
        fifth,
        "pear"
      )

    } yield finalList
  }
}