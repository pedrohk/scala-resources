package pedrohk.injection

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import pedrohk.injection.config.InjectionConfiguration

object Application {

  def main(
            args: Array[String]
          ): Unit = {

    val context =
      new AnnotationConfigApplicationContext(
        classOf[
          InjectionConfiguration
        ]
      )

    context.close()
  }

}