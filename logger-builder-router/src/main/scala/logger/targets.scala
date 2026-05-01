package logger

import java.io.{File, FileWriter}
import java.util.concurrent.ConcurrentLinkedQueue
import scala.jdk.CollectionConverters.*

trait LogTarget {
  def send(message: LogMessage): Unit
}

class FileSystemLogTarget(path: String) extends LogTarget {
  private val file = new File(path)
  if (!file.exists()) { file.createNewFile() }

  override def send(message: LogMessage): Unit = {
    val writer = new FileWriter(file, true)
    try {
      writer.write(s"${message.timestamp} [${message.level}] ${message.message}\n")
    } finally {
      writer.close()
    }
  }
}

class InMemoryLogTarget extends LogTarget {
  private val queue = new ConcurrentLinkedQueue[LogMessage]()

  override def send(message: LogMessage): Unit = {
    queue.add(message)
  }

  def messages: List[LogMessage] = {
    queue.iterator().asScala.toList
  }
}

class CompositeLogTarget(targets: List[LogTarget]) extends LogTarget {
  override def send(message: LogMessage): Unit = {
    targets.foreach(t => t.send(message))
  }
}