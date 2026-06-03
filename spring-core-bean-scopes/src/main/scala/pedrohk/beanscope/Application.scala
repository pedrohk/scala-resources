package pedrohk.beanscope

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import pedrohk.beanscope.config.BeanScopeConfiguration
import pedrohk.beanscope.service.PrototypeSessionService
import pedrohk.beanscope.service.SingletonWorkspaceService

object Application {

  def main(
            args: Array[String]
          ): Unit = {

    val context =
      new AnnotationConfigApplicationContext(
        classOf[BeanScopeConfiguration]
      )

    context.getBean(
      classOf[SingletonWorkspaceService]
    )

    context.getBean(
      classOf[PrototypeSessionService]
    )

    context.close()
  }

}