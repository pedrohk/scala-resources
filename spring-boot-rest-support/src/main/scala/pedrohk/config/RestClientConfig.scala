package pedrohk.config

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.web.client.RestTemplate

@Configuration
class RestClientConfig {

  @Bean
  def restTemplate(): RestTemplate = {
    new RestTemplate()
  }
}