package pedrohk.springvalue.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer

@Configuration
class ValueConfiguration

object ValueConfiguration {

  @Bean
  def propertySourcesPlaceholderConfigurer()
  : PropertySourcesPlaceholderConfigurer = {

    new PropertySourcesPlaceholderConfigurer()

  }

}