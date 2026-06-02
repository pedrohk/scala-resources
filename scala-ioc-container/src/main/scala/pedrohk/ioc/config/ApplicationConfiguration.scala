package pedrohk.ioc.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pedrohk.ioc.model.DeveloperProfile
import pedrohk.ioc.model.PlatformTeam
import pedrohk.ioc.repository.InMemoryProfileRepository
import pedrohk.ioc.repository.ProfileRepository
import pedrohk.ioc.service.ProfileService
import pedrohk.ioc.service.TeamService

@Configuration
class ApplicationConfiguration {

  @Bean
  def profileRepository(): ProfileRepository = {
    new InMemoryProfileRepository()
  }

  @Bean
  def profileService(
                      profileRepository: ProfileRepository
                    ): ProfileService = {
    new ProfileService(profileRepository)
  }

  @Bean
  def platformTeam(): PlatformTeam = {
    new PlatformTeam("Lia Platform Team")
  }

  @Bean
  def teamService(
                   platformTeam: PlatformTeam
                 ): TeamService = {
    new TeamService(platformTeam)
  }

  @Bean
  def developerProfile(): DeveloperProfile = {
    new DeveloperProfile(
      "Pedro Henrique",
      "Spring Core"
    )
  }

}