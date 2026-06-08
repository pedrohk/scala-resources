package pedrohk.springvalue.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pedrohk.springvalue.model.ProfileSummary
import pedrohk.springvalue.service.ProfileInformationFacade
import pedrohk.springvalue.service.ProfileSummaryService
import pedrohk.springvalue.service.ProfileValueService

@Configuration
class ProfileBeans {

  @Bean
  def profileSummary(
                      service: ProfileSummaryService
                    ): ProfileSummary = {

    service.createSummary()

  }

  @Bean
  def profileInformationFacade(
                                values: ProfileValueService,
                                summaries: ProfileSummaryService
                              ): ProfileInformationFacade = {

    new ProfileInformationFacade(
      values,
      summaries
    )

  }

}