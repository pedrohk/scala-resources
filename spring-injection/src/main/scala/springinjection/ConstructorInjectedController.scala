package springinjection {

  class ConstructorInjectedController(private val messageService: MessageService) {

    def processMessage: String = {
      messageService.getMessage
    }

  }

}
