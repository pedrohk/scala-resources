package pedrohk.springvalue.service

import org.springframework.stereotype.Service
import pedrohk.springvalue.model.ProfileSummary

@Service
class ProfileSummaryService(
                             profileValueService: ProfileValueService
                           ) {

  def createSummary(): ProfileSummary = {

    val profile =
      profileValueService
        .buildProfile()

    new ProfileSummary(
      s"${profile.owner} works in ${profile.team} with ${profile.mentor} (${profile.environment})"
    )
  }

  def summaryText(): String = {
    createSummary().summary
  }

}