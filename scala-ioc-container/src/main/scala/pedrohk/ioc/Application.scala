package pedrohk.ioc

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import pedrohk.ioc.config.ApplicationConfiguration

object Application {

  def main(args: Array[String]): Unit = {
    val context =
      new AnnotationConfigApplicationContext(
        classOf[ApplicationConfiguration]
      )

    context.close()
  }

}