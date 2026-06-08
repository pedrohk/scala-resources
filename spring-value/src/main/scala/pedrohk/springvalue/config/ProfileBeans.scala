package pedrohk.springvalue.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pedrohk.springvalue.model.ProfileSummary
import pedrohk.springvalue.service.ProfileSummaryService

@Configuration
class ProfileBeans {

  @Bean
  def profileSummary(
                      profileSummaryService: ProfileSummaryService
                    ): ProfileSummary = {

    profileSummaryService
      .createSummary()
  }

}