package pedrohk.beanprocessor.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pedrohk.beanprocessor.processor.ProfileInitializationPostProcessor
import pedrohk.beanprocessor.service.DeveloperProfileService
import pedrohk.beanprocessor.model.InitializationAudit

@Configuration
class BeanProcessorConfiguration {

  @Bean
  def profileInitializationPostProcessor():
  ProfileInitializationPostProcessor = {

    new ProfileInitializationPostProcessor()
  }

  @Bean
  def developerProfileService():
  DeveloperProfileService = {

    new DeveloperProfileService()
  }

  @Bean
  def initializationAudit(
                           service:
                           DeveloperProfileService
                         ): InitializationAudit = {

    new InitializationAudit(
      service.isInitialized(),
      service.currentOwner()
    )
  }

}