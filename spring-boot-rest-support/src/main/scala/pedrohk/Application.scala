package pedrohk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication

@SpringBootApplication
class Application

object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args*)
  }
}