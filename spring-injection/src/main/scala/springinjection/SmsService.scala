package springinjection

class SmsService extends MessageService {
  def getMessage: String = {
    "SMS Service Message"
  }
}