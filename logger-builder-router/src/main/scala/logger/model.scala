package logger

enum LogLevel {
  case INFO, WARN, ERROR
}

case class LogMessage(
                       level: LogLevel,
                       message: String,
                       timestamp: Long = System.currentTimeMillis()
                     )