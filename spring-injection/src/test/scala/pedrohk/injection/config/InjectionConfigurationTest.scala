package pedrohk.injection.config

import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import pedrohk.injection.repository.ProfileRepository
import pedrohk.injection.service.*

class InjectionConfigurationTest
  extends AnyFlatSpec
    with Matchers
    with BeforeAndAfterAll {

  private val context =
    new AnnotationConfigApplicationContext(
      classOf[
        InjectionConfiguration
      ]
    )

  override def afterAll(): Unit = {
    context.close()
  }

  "Spring configuration" should "create repository bean" in {

    context.getBean(
      classOf[
        ProfileRepository
      ]
    ) should not be null
  }

  it should "create constructor service" in {

    context.getBean(
      classOf[
        ConstructorInjectedProfileService
      ]
    ) should not be null
  }

  it should "create setter service" in {

    context.getBean(
      classOf[
        SetterInjectedProfileService
      ]
    ) should not be null
  }

  it should "wire constructor injection correctly" in {

    val service =
      context.getBean(
        classOf[
          ConstructorInjectedProfileService
        ]
      )

    service
      .load(
        1L
      )
      .get
      .mentor shouldBe
      "Lia"
  }

  it should "wire setter injection correctly" in {

    val service =
      context.getBean(
        classOf[
          SetterInjectedProfileService
        ]
      )

    service
      .load(
        2L
      )
      .get
      .name shouldBe
      "Caio Ventura"
  }

}