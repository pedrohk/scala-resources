package pedrohk.security.service

  import org.scalatest.flatspec.AnyFlatSpec
  import org.scalatest.matchers.should.Matchers

  class DeveloperSessionServiceTest
    extends AnyFlatSpec
      with Matchers {

    "buildSession" should "create a session with correct values" in {

      val developerSessionService =
        new DeveloperSessionService

      val result =
        developerSessionService.buildSession(
          "Pedro Henrique",
          "Secure Platform"
        )

      result.owner shouldBe "Pedro Henrique"
      result.activeProject shouldBe "Secure Platform"
    }

    "validateProject" should "return true for valid project" in {

      val developerSessionService =
        new DeveloperSessionService

      val result =
        developerSessionService.validateProject(
          "LiaSecure"
        )

      result shouldBe true
    }

    it should "return false for invalid project" in {

      val developerSessionService =
        new DeveloperSessionService

      val result =
        developerSessionService.validateProject(
          ""
        )

      result shouldBe false
    }
  }