package springscopes {

  import org.scalatest.funsuite.AnyFunSuite
  import org.scalatest.matchers.should.Matchers

  class BeanFactoryTest extends AnyFunSuite with Matchers {

    test("Singleton scope should return the exact same instance on consecutive calls") {
      val factory = new BeanFactory()
      val singletonInstance = new SingletonBean("Shared instance")

      factory.registerSingleton("mySingleton", singletonInstance)

      val firstCall = factory.getBean("mySingleton")
      val secondCall = factory.getBean("mySingleton")

      firstCall should be theSameInstanceAs secondCall
      firstCall.instanceId shouldBe secondCall.instanceId
    }

    test("Prototype scope should return a brand new instance on consecutive calls") {
      val factory = new BeanFactory()

      factory.registerPrototype("myPrototype", () => new PrototypeBean("New instance every time"))

      val firstCall = factory.getBean("myPrototype")
      val secondCall = factory.getBean("myPrototype")

      firstCall shouldNot be theSameInstanceAs secondCall
      firstCall.instanceId shouldNot be(secondCall.instanceId)
    }

    test("BeanFactory should throw NoSuchElementException when requesting an unregistered bean") {
      val factory = new BeanFactory()

      assertThrows[NoSuchElementException] {
        factory.getBean("unknownBean")
      }
    }

  }
}
