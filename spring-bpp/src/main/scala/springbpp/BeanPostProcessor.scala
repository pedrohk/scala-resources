package springbpp {

  trait BeanPostProcessor {
    def postProcessBeforeInitialization(bean: AnyRef, beanName: String): AnyRef = {
      bean
    }

    def postProcessAfterInitialization(bean: AnyRef, beanName: String): AnyRef = {
      bean
    }
  }

}
