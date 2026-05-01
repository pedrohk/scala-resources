package logger

import scala.concurrent.{ExecutionContext, Future}

enum Mode {
  case SYNC, ASYNC
}

class LogDispatcher(target: LogTarget, mode: Mode)(using ec: ExecutionContext) {

  def dispatch(message: LogMessage): Unit = {
    mode match {
      case Mode.SYNC => target.send(message)
      case Mode.ASYNC => Future { target.send(message) }
    }
  }
}