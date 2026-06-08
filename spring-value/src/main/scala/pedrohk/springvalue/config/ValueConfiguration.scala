package pedrohk.springvalue.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer

@Configuration
@ComponentScan(
  Array(
    "pedrohk.springvalue"
  )
)
class ValueConfiguration {

  @Bean
  def propertySourcesPlaceholderConfigurer()
  : PropertySourcesPlaceholderConfigurer = {

    new PropertySourcesPlaceholderConfigurer()
  }

}