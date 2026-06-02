package pedrohk.ioc.config

import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import pedrohk.ioc.model.DeveloperProfile
import pedrohk.ioc.model.PlatformTeam
import pedrohk.ioc.service.ProfileService
import pedrohk.ioc.service.TeamService

class ApplicationConfigurationTest
  extends AnyFlatSpec
    with Matchers
    with BeforeAndAfterAll {

  private val context =
    new AnnotationConfigApplicationContext(
      classOf[ApplicationConfiguration]
    )

  override def afterAll(): Unit = {
    context.close()
  }

  "Spring IoC container" should "create ProfileService bean" in {
    val service =
      context.getBean(classOf[ProfileService])

    service.profileName() shouldBe
      "Pedro Henrique"
  }

  it should "create TeamService bean" in {
    val service =
      context.getBean(classOf[TeamService])

    service.teamLabel() shouldBe
      "Lia Platform Team"
  }

  it should "create DeveloperProfile bean" in {
    val profile =
      context.getBean(classOf[DeveloperProfile])

    profile.fullName shouldBe
      "Pedro Henrique"

    profile.specialization shouldBe
      "Spring Core"
  }

  it should "create PlatformTeam bean" in {
    val team =
      context.getBean(classOf[PlatformTeam])

    team.teamName shouldBe
      "Lia Platform Team"
  }

}