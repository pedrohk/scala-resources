package logger

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.concurrent.Eventually._
import org.scalatest.time.{Millis, Seconds, Span}
import scala.concurrent.ExecutionContext.Implicits.global
import java.io.File

class LoggerSpec extends AnyFunSuite {

  given PatienceConfig =
    PatienceConfig(timeout = Span(3, Seconds), interval = Span(50, Millis))

  test("sync logging writes immediately") {
    val target = new InMemoryLogTarget
    val logger = LoggerBuilder.build(target, Mode.SYNC)

    logger.info("hello")

    assert(target.messages.size == 1)
    assert(target.messages.head.message == "hello")
  }

  test("async logging eventually writes") {
    val target = new InMemoryLogTarget
    val logger = LoggerBuilder.build(target, Mode.ASYNC)

    logger.info("async")

    eventually {
      assert(target.messages.nonEmpty)
      assert(target.messages.head.message == "async")
    }
  }

  test("multiple log levels") {
    val target = new InMemoryLogTarget
    val logger = LoggerBuilder.build(target, Mode.SYNC)

    logger.info("i")
    logger.warn("w")
    logger.error("e")

    val levels = target.messages.map(_.level)
    assert(levels == List(LogLevel.INFO, LogLevel.WARN, LogLevel.ERROR))
  }

  test("composite target logs to multiple destinations") {
    val t1 = new InMemoryLogTarget
    val t2 = new InMemoryLogTarget
    val composite = new CompositeLogTarget(List(t1, t2))
    val logger = LoggerBuilder.build(composite, Mode.SYNC)

    logger.info("multi")

    assert(t1.messages.size == 1)
    assert(t2.messages.size == 1)
  }

  test("file system logging writes to file") {
    val file = File.createTempFile("log-test", ".txt")
    val target = new FileSystemLogTarget(file.getAbsolutePath)
    val logger = LoggerBuilder.build(target, Mode.SYNC)

    logger.info("file-test")

    val content = scala.io.Source.fromFile(file).getLines().toList
    assert(content.exists(_.contains("file-test")))
  }

  test("async composite logging works") {
    val t1 = new InMemoryLogTarget
    val t2 = new InMemoryLogTarget
    val composite = new CompositeLogTarget(List(t1, t2))
    val logger = LoggerBuilder.build(composite, Mode.ASYNC)

    logger.error("boom")

    eventually {
      assert(t1.messages.nonEmpty)
      assert(t2.messages.nonEmpty)
    }
  }

  test("empty message still logs") {
    val target = new InMemoryLogTarget
    val logger = LoggerBuilder.build(target, Mode.SYNC)

    logger.info("")

    assert(target.messages.head.message == "")
  }

  test("high volume sync logging") {
    val target = new InMemoryLogTarget
    val logger = LoggerBuilder.build(target, Mode.SYNC)

    (1 to 1000).foreach(i => logger.info(s"msg-$i"))

    assert(target.messages.size == 1000)
  }

  test("high volume async logging") {
    val target = new InMemoryLogTarget
    val logger = LoggerBuilder.build(target, Mode.ASYNC)

    (1 to 1000).foreach(i => logger.info(s"msg-$i"))

    eventually {
      assert(target.messages.size == 1000)
    }
  }

  test("timestamp is assigned") {
    val target = new InMemoryLogTarget
    val logger = LoggerBuilder.build(target, Mode.SYNC)

    logger.info("time")

    assert(target.messages.head.timestamp > 0)
  }
}