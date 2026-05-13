package ioc

@Component
class NotificationService {

  def send(message: String): String = {
    s"sent:$message"
  }
}