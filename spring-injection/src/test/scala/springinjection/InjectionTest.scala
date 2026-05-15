package springinjection {

  import org.scalatest.funsuite.AnyFunSuite
  import org.scalatest.matchers.should.Matchers

  class InjectionTest extends AnyFunSuite with Matchers {

    test("Constructor Injection should successfully initialize and delegate behavior") {
      val emailService = new EmailService()
      val controller = new ConstructorInjectedController(emailService)

      val result = controller.processMessage

      result shouldBe "Email Service Message"
    }

    test("Setter Injection should successfully inject dependency and delegate behavior after initialization") {
      val smsService = new SmsService()
      val controller = new SetterInjectedController()

      controller.setMessageService(smsService)
      val result = controller.processMessage

      result shouldBe "SMS Service Message"
    }

    test("Setter Injection should throw IllegalStateException if processMessage is invoked before injection") {
      val controller = new SetterInjectedController()

      assertThrows[IllegalStateException] {
        controller.processMessage
      }
    }

    test("Setter Injection should allow swapping dependencies dynamically") {
      val controller = new SetterInjectedController()
      val emailService = new EmailService()
      val smsService = new SmsService()

      controller.setMessageService(emailService)
      controller.processMessage shouldBe "Email Service Message"

      controller.setMessageService(smsService)
      controller.processMessage shouldBe "SMS Service Message"
    }

  }
}
