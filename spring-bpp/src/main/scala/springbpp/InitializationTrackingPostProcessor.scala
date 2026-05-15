package springbpp {

  class InitializationTrackingPostProcessor extends BeanPostProcessor {

    private val trackedBeans: java.util.HashSet[String] = new java.util.HashSet[String]()

    override def postProcessBeforeInitialization(bean: AnyRef, beanName: String): AnyRef = {
      bean
    }

    override def postProcessAfterInitialization(bean: AnyRef, beanName: String): AnyRef = {
      bean match {
        case initBean: Initializable => {
          if (initBean.isInitialized) {
            trackedBeans.add(beanName)
          }
          bean
        }
        case _ => {
          bean
        }
      }
    }

    def isTracked(beanName: String): Boolean = {
      trackedBeans.contains(beanName)
    }
  }

}
