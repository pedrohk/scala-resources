package pedrohk.beanscope.config

import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import pedrohk.beanscope.model.DeveloperWorkspace
import pedrohk.beanscope.model.ScopeSnapshot
import pedrohk.beanscope.service.PrototypeSessionService
import pedrohk.beanscope.service.SingletonWorkspaceService

class BeanScopeConfigurationTest
  extends AnyFlatSpec
    with Matchers
    with BeforeAndAfterAll {

  private val context =
    new AnnotationConfigApplicationContext(
      classOf[BeanScopeConfiguration]
    )

  override def afterAll(): Unit = {
    context.close()
  }

  "Spring container" should "reuse singleton bean" in {

    val first =
      context.getBean(
        classOf[DeveloperWorkspace]
      )

    val second =
      context.getBean(
        classOf[DeveloperWorkspace]
      )

    first should be theSameInstanceAs second
  }

  it should "create different prototype snapshots" in {

    val first =
      context.getBean(
        classOf[ScopeSnapshot]
      )

    val second =
      context.getBean(
        classOf[ScopeSnapshot]
      )

    first should not be theSameInstanceAs(second)

    first.prototypeId should not equal
      second.prototypeId
  }

  it should "reuse singleton service" in {

    val first =
      context.getBean(
        classOf[SingletonWorkspaceService]
      )

    val second =
      context.getBean(
        classOf[SingletonWorkspaceService]
      )

    first should be theSameInstanceAs second
  }

  it should "create different prototype services" in {

    val first =
      context.getBean(
        classOf[PrototypeSessionService]
      )

    val second =
      context.getBean(
        classOf[PrototypeSessionService]
      )

    first should not be theSameInstanceAs(second)
  }

  it should "inject singleton into prototype" in {

    val first =
      context.getBean(
        classOf[PrototypeSessionService]
      )

    val second =
      context.getBean(
        classOf[PrototypeSessionService]
      )

    first.singletonReference() shouldBe
      second.singletonReference()
  }

  it should "create valid singleton owner" in {

    val service =
      context.getBean(
        classOf[SingletonWorkspaceService]
      )

    service.owner() shouldBe
      "Pedro Henrique"
  }

}