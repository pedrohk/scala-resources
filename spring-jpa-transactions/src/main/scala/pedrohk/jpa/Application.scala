package pedrohk.jpa

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class Application

object Application {

  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args*)
  }
}