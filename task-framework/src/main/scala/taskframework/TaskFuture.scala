package taskframework

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicReference

class TaskFuture[T] {

  private val latch = new CountDownLatch(1)

  private val result =
    new AtomicReference[TaskResult[T]](
      TaskResult[T](
        None,
        None,
        completed = false
      )
    )

  def complete(value: T): Unit = {
    result.set(
      TaskResult(
        Some(value),
        None,
        completed = true
      )
    )

    latch.countDown()
  }

  def fail(error: Throwable): Unit = {
    result.set(
      TaskResult(
        None,
        Some(error),
        completed = true
      )
    )

    latch.countDown()
  }

  def await(): TaskResult[T] = {
    latch.await()
    result.get()
  }

  def await(timeoutMillis: Long): TaskResult[T] = {
    latch.await(timeoutMillis, TimeUnit.MILLISECONDS)
    result.get()
  }

  def isCompleted(): Boolean = {
    result.get().completed
  }
}