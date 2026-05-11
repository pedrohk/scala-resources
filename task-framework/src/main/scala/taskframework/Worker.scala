package taskframework

import java.util.concurrent.BlockingQueue
import java.util.concurrent.atomic.AtomicBoolean

class Worker(
              queue: BlockingQueue[() => Unit],
              running: AtomicBoolean
            ) extends Runnable {

  override def run(): Unit = {

    while (running.get() || !queue.isEmpty) {

      val task = queue.poll()

      if (task != null) {
        task()
      } else {
        Thread.sleep(5)
      }
    }
  }
}