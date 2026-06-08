package pedrohk.springvalue

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import pedrohk.springvalue.config.ValueConfiguration

object Application {

  def main(args: Array[String]): Unit = {

    val context =
      new AnnotationConfigApplicationContext(
        classOf[ValueConfiguration]
      )

    context.close()
  }

}