package pedrohk.injection.config

import org.springframework.context.annotation.{Bean, Configuration}
import pedrohk.injection.repository.InMemoryProfileRepository
import pedrohk.injection.service.{
  ConstructorInjectedProfileService,
  SetterInjectedProfileService
}
import pedrohk.injection.repository.ProfileRepository

@Configuration
class InjectionConfiguration {

  @Bean
  def constructorInjectedProfileService():
  ConstructorInjectedProfileService = {

    new ConstructorInjectedProfileService(
      repository()
    )
  }

  @Bean
  def setterInjectedProfileService():
  SetterInjectedProfileService = {

    val service =
      new SetterInjectedProfileService()

    service.setRepository(
      repository()
    )

    service
  }

  @Bean
  def repository():
  ProfileRepository = {

    new InMemoryProfileRepository()
  }

}