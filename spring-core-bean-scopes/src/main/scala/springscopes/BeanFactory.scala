package springscopes {

  class BeanFactory {
    private val singletonRegistry: java.util.concurrent.ConcurrentHashMap[String, Bean] =
      new java.util.concurrent.ConcurrentHashMap()

    private val providers: java.util.concurrent.ConcurrentHashMap[String, () => Bean] =
      new java.util.concurrent.ConcurrentHashMap()

    def registerSingleton(name: String, instance: Bean): Unit = {
      singletonRegistry.put(name, instance)
    }

    def registerPrototype(name: String, provider: () => Bean): Unit = {
      providers.put(name, provider)
    }

    def getBean(name: String): Bean = {
      if (singletonRegistry.containsKey(name)) {
        singletonRegistry.get(name)
      } else if (providers.containsKey(name)) {
        providers.get(name)()
      } else {
        throw new NoSuchElementException(s"No bean registered under name: $name")
      }
    }
  }

}
