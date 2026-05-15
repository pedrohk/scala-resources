package springbpp {

  class PrefixValidationPostProcessor extends BeanPostProcessor {

    override def postProcessBeforeInitialization(bean: AnyRef, beanName: String): AnyRef = {
      bean match {
        case service: GreetingService => {
          if (service.getPrefix == null || service.getPrefix.isEmpty) {
            service.setPrefix("Hello, ")
          }
          service
        }
        case _ => {
          bean
        }
      }
    }

    override def postProcessAfterInitialization(bean: AnyRef, beanName: String): AnyRef = {
      bean
    }
  }

}
