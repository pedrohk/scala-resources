package springbpp {

  class Container {
    private val beans: java.util.HashMap[String, AnyRef] = new java.util.HashMap[String, AnyRef]()
    private val processors: java.util.ArrayList[BeanPostProcessor] = new java.util.ArrayList[BeanPostProcessor]()

    def registerBean(name: String, bean: AnyRef): Unit = {
      beans.put(name, bean)
    }

    def addBeanPostProcessor(processor: BeanPostProcessor): Unit = {
      processors.add(processor)
    }

    def getBean(name: String): AnyRef = {
      val rawBean = beans.get(name)
      if (rawBean == null) {
        throw new NoSuchElementException(s"No bean found: $name")
      }

      var currentBean = rawBean

      val beforeIterator = processors.iterator()
      while (beforeIterator.hasNext) {
        currentBean = beforeIterator.next().postProcessBeforeInitialization(currentBean, name)
      }

      currentBean match {
        case initBean: Initializable => {
          initBean.initialize()
        }
        case _ => {}
      }

      val afterIterator = processors.iterator()
      while (afterIterator.hasNext) {
        currentBean = afterIterator.next().postProcessAfterInitialization(currentBean, name)
      }

      currentBean
    }
  }

}
