package pedrohk.security.controller

  import org.scalatest.flatspec.AnyFlatSpec
  import org.scalatest.matchers.should.Matchers
  import pedrohk.security.service.DeveloperSessionService
  import pedrohk.security.model.DeveloperSession

  class DeveloperSessionControllerTest
    extends AnyFlatSpec
      with Matchers {

    "status" should "return csrf protection message" in {

      val developerSessionController =
        new DeveloperSessionController(
          new DeveloperSessionService
        )

      val response =
        developerSessionController.status()

      response.getBody shouldBe
        "CSRF protection enabled"
    }

    "createSession" should "create a valid session" in {

      val developerSessionController =
        new DeveloperSessionController(
          new DeveloperSessionService
        )

      val request =
        new DeveloperSession(
          "Lia Kuhn",
          "Spring Fortress"
        )

      val response =
        developerSessionController.createSession(
          request
        )

      response.getBody.owner shouldBe
        "Lia Kuhn"

      response.getBody.activeProject shouldBe
        "Spring Fortress"
    }
  }