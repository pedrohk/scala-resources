package pedrohk.cache

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class Application

object Application {

  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args*)
  }
}