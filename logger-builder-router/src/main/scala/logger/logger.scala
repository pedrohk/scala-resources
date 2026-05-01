package logger

import scala.concurrent.ExecutionContext

class Logger private[logger] (dispatcher: LogDispatcher) {

  def info(message: String): Unit = {
    dispatcher.dispatch(LogMessage(LogLevel.INFO, message))
  }

  def warn(message: String): Unit = {
    dispatcher.dispatch(LogMessage(LogLevel.WARN, message))
  }

  def error(message: String): Unit = {
    dispatcher.dispatch(LogMessage(LogLevel.ERROR, message))
  }
}

object LoggerBuilder {

  def build(target: LogTarget, mode: Mode)(using ec: ExecutionContext): Logger = {
    val dispatcher = new LogDispatcher(target, mode)
    new Logger(dispatcher)
  }
}