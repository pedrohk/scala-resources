package taskframework

class SimpleTask[T](
                     computation: () => T
                   ) extends Task[T] {

  override def execute(): T = {
    computation()
  }
}