package pedrohk.beanprocessor

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import pedrohk.beanprocessor.config.BeanProcessorConfiguration

object Application {

  def main(
            args: Array[String]
          ): Unit = {

    val context =
      new AnnotationConfigApplicationContext(
        classOf[
          BeanProcessorConfiguration
        ]
      )

    context.getBean(
      "developerProfileService"
    )

    context.close()
  }

}