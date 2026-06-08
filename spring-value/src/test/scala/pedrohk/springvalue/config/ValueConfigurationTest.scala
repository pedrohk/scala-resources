package pedrohk.springvalue.config

import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.support.ResourcePropertySource
import pedrohk.springvalue.model.ProfileSummary
import pedrohk.springvalue.service.ProfileInformationFacade
import pedrohk.springvalue.service.ProfileSummaryService
import pedrohk.springvalue.service.ProfileValueService

class ValueConfigurationTest
  extends AnyFunSuite
    with Matchers
    with BeforeAndAfterAll {

  private val context =
    new AnnotationConfigApplicationContext()

  context
    .getEnvironment
    .getPropertySources
    .addLast(
      new ResourcePropertySource(
        new ClassPathResource(
          "application.properties"
        )
      )
    )

  context.register(
    classOf[ValueConfiguration],
    classOf[ProfileBeans],
    classOf[ProfileValueService],
    classOf[ProfileSummaryService]
  )

  context.refresh()

  override def afterAll(): Unit = {
    context.close()
  }

  test(
    "shouldInjectValuesFromSpringContext"
  ) {

    val service =
      context.getBean(
        classOf[
          ProfileValueService
        ]
      )

    service.ownerName() shouldBe
      "Pedro Henrique"
  }

  test(
    "shouldCreateSummaryBeanFromSpringContext"
  ) {

    val summary =
      context.getBean(
        classOf[
          ProfileSummary
        ]
      )

    summary.summary shouldBe
      "Pedro Henrique works in Platform with Lia (production)"
  }

  test(
    "shouldCreateFacadeFromSpringContext"
  ) {

    val facade =
      context.getBean(
        classOf[
          ProfileInformationFacade
        ]
      )

    facade
      .profileDescription()
      .contains(
        "Pedro Henrique"
      ) shouldBe true
  }

}