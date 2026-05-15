package springbpp {

  import org.scalatest.funsuite.AnyFunSuite
  import org.scalatest.matchers.should.Matchers

  class BeanPostProcessorTest extends AnyFunSuite with Matchers {

    test("Container should execute before-initialization lifecycle modifications") {
      val container = new Container()
      val validator = new PrefixValidationPostProcessor()
      val service = new GreetingService()

      container.addBeanPostProcessor(validator)
      container.registerBean("greetingService", service)

      val resolved = container.getBean("greetingService").asInstanceOf[GreetingService]

      resolved.getPrefix shouldBe "Hello, "
      resolved.greet("Sarah") shouldBe "Hello, Sarah"
    }

    test("Container should preserve custom set fields during before-initialization phase") {
      val container = new Container()
      val validator = new PrefixValidationPostProcessor()
      val service = new GreetingService()
      service.setPrefix("Welcome, ")

      container.addBeanPostProcessor(validator)
      container.registerBean("customGreetingService", service)

      val resolved = container.getBean("customGreetingService").asInstanceOf[GreetingService]

      resolved.getPrefix shouldBe "Welcome, "
      resolved.greet("Pedro") shouldBe "Welcome, Pedro"
    }

    test("Container should execute initializable contract lifecycle callbacks") {
      val container = new Container()
      val service = new GreetingService()

      container.registerBean("rawService", service)
      service.isInitialized shouldBe false

      val resolved = container.getBean("rawService").asInstanceOf[GreetingService]
      resolved.isInitialized shouldBe true
    }

    test("Container should execute after-initialization lifecycle tracking hooks") {
      val container = new Container()
      val tracker = new InitializationTrackingPostProcessor()
      val service = new GreetingService()

      container.addBeanPostProcessor(tracker)
      container.registerBean("trackedService", service)

      tracker.isTracked("trackedService") shouldBe false

      container.getBean("trackedService")
      tracker.isTracked("trackedService") shouldBe true
    }

    test("Container should crash when requesting an unregistered bean identity") {
      val container = new Container()

      assertThrows[NoSuchElementException] {
        container.getBean("missingBean")
      }
    }

  }
}
