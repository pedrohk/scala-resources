package springinjection

class EmailService extends MessageService {
  def getMessage: String = {
    "Email Service Message"
  }
}