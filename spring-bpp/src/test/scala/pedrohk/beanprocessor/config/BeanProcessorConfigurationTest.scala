package pedrohk.beanprocessor.config

import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import pedrohk.beanprocessor.model.InitializationAudit
import pedrohk.beanprocessor.processor.ProfileInitializationPostProcessor
import pedrohk.beanprocessor.service.DeveloperProfileService

class BeanProcessorConfigurationTest
  extends AnyFunSuite
    with Matchers
    with BeforeAndAfterAll {

  private val context =
    new AnnotationConfigApplicationContext(
      classOf[
        BeanProcessorConfiguration
      ]
    )

  override def afterAll(): Unit = {
    context.close()
  }

  test("ShouldCreatePostProcessorBean") {

    context.getBean(
      classOf[
        ProfileInitializationPostProcessor
      ]
    ) should not be null
  }

  test("ShouldCreateServiceBean") {

    context.getBean(
      classOf[
        DeveloperProfileService
      ]
    ) should not be null
  }

  test("ShouldApplyBeanPostProcessorAutomatically") {

    val service =
      context.getBean(
        classOf[
          DeveloperProfileService
        ]
      )

    service.currentOwner() shouldBe
      "Pedro Henrique"

    service.isInitialized() shouldBe
      true
  }

  test("ShouldCreateAuditBeanUsingProcessedValues") {

    val audit =
      context.getBean(
        classOf[
          InitializationAudit
        ]
      )

    audit.owner shouldBe
      "Pedro Henrique"

    audit.initialized shouldBe
      true
  }

  test("ShouldKeepProcessedSummaryConsistent") {

    val service =
      context.getBean(
        classOf[
          DeveloperProfileService
        ]
      )

    service.summary() shouldBe
      "Pedro Henrique:true"
  }

}