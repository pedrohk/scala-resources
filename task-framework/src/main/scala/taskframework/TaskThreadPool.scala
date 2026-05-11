package taskframework

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.atomic.AtomicBoolean

class TaskThreadPool(
                      threadCount: Int
                    ) {

  require(threadCount > 0)

  private val queue =
    new LinkedBlockingQueue[() => Unit]()

  private val running =
    new AtomicBoolean(true)

  private val workers =
    (1 to threadCount).map { index =>

      val worker =
        new Worker(queue, running)

      val thread =
        new Thread(worker)

      thread.setName(s"task-worker-$index")
      thread.start()

      thread
    }

  def submit[T](task: Task[T]): TaskFuture[T] = {

    val future = new TaskFuture[T]

    queue.offer(() => {

      try {

        val result = task.execute()

        future.complete(result)

      } catch {

        case ex: Throwable => {
          future.fail(ex)
        }
      }
    })

    future
  }

  def queueSize(): Int = {
    queue.size()
  }

  def workerCount(): Int = {
    workers.size
  }

  def shutdown(): Unit = {
    running.set(false)

    workers.foreach(_.join())
  }

  def isRunning(): Boolean = {
    running.get()
  }
}