package taskframework

case class TaskResult[T](
                          value: Option[T],
                          error: Option[Throwable],
                          completed: Boolean
                        )