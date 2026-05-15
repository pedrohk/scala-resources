package springinjection {

  class SetterInjectedController {

    private var messageService: MessageService = null

    def setMessageService(service: MessageService): Unit = {
      this.messageService = service
    }

    def processMessage: String = {
      if (messageService == null) {
        throw new IllegalStateException("Dependency 'messageService' has not been initialized via setter injection.")
      }
      messageService.getMessage
    }

  }

}
