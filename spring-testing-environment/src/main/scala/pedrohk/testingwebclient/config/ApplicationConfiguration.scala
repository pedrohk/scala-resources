package pedrohk.testingwebclient.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration {

  @Bean
  def ownerName(): String = {
    "Lia"
  }

}