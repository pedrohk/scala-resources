package taskframework

trait Task[T] {

  def execute(): T
}