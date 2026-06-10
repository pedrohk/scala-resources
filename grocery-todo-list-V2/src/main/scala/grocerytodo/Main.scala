package grocerytodo

object Main {

  @main
  def run(): Unit = {

    val result =
      GroceryTodoService
        .workflow()

    result.foreach { list =>
      list
        .listAll
        .foreach(
          println
        )
    }
  }
}