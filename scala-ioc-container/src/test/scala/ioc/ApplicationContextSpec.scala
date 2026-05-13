package ioc

import org.scalatest.funsuite.AnyFunSuite

class ApplicationContextSpec extends AnyFunSuite {

  private def createContext(): ApplicationContext = {

    new ApplicationContext(
      Seq(
        classOf[UserRepository],
        classOf[UserService],
        classOf[NotificationService],
        classOf[ApplicationService]
      )
    )
  }

  test("should register all beans") {

    val context = createContext()

    assert(context.containsBean("UserRepository"))
    assert(context.containsBean("UserService"))
    assert(context.containsBean("NotificationService"))
    assert(context.containsBean("ApplicationService"))
  }

  test("should retrieve user repository bean") {

    val context = createContext()

    val repository =
      context.getBean(classOf[UserRepository])

    assert(repository != null)
    assert(repository.findUser(1) == "user-1")
  }

  test("should retrieve user service bean") {

    val context = createContext()

    val service =
      context.getBean(classOf[UserService])

    assert(service != null)
    assert(service.getUser(2) == "user-2")
  }

  test("should inject repository into user service") {

    val context = createContext()

    val service =
      context.getBean(classOf[UserService])

    assert(service.userRepository != null)
    assert(service.userRepository.findUser(3) == "user-3")
  }

  test("should inject dependencies into application service") {

    val context = createContext()

    val service =
      context.getBean(classOf[ApplicationService])

    assert(service.userService != null)
    assert(service.notificationService != null)
  }

  test("should execute application service flow") {

    val context = createContext()

    val service =
      context.getBean(classOf[ApplicationService])

    val result = service.process(10)

    assert(result == "sent:user-10")
  }

  test("should return correct bean names") {

    val context = createContext()

    val names =
      context.getBeanDefinitionNames()

    assert(
      names ==
        List(
          "ApplicationService",
          "NotificationService",
          "UserRepository",
          "UserService"
        )
    )
  }

  test("should return correct bean count") {

    val context = createContext()

    assert(context.registeredBeansCount() == 4)
  }

  test("should reuse singleton instances") {

    val context = createContext()

    val service1 =
      context.getBean(classOf[UserService])

    val service2 =
      context.getBean(classOf[UserService])

    assert(service1 eq service2)
  }

  test("should throw exception for unknown bean") {

    val context = createContext()

    assertThrows[IllegalArgumentException] {
      context.getBean(classOf[String])
    }
  }

  test("should validate repository exists logic") {

    val context = createContext()

    val repository =
      context.getBean(classOf[UserRepository])

    assert(repository.exists(1))
    assert(!repository.exists(0))
    assert(!repository.exists(-1))
  }

  test("should validate user existence through service") {

    val context = createContext()

    val service =
      context.getBean(classOf[UserService])

    assert(service.userExists(5))
    assert(!service.userExists(-10))
  }

  test("should repeatedly resolve beans") {

    val context = createContext()

    (1 to 100).foreach { _ =>

      val service =
        context.getBean(classOf[UserService])

      assert(service.getUser(1) == "user-1")
    }
  }

  test("should resolve nested dependency graph") {

    val context = createContext()

    val applicationService =
      context.getBean(classOf[ApplicationService])

    val result =
      applicationService.process(99)

    assert(result == "sent:user-99")
  }

  test("should retrieve notification service") {

    val context = createContext()

    val notificationService =
      context.getBean(classOf[NotificationService])

    assert(
      notificationService.send("hello") ==
        "sent:hello"
    )
  }
}