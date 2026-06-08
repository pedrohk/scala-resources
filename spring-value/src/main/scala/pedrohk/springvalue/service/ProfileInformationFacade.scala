package pedrohk.springvalue.service

import org.springframework.stereotype.Service

@Service
class ProfileInformationFacade(
                                profileValueService: ProfileValueService,
                                profileSummaryService: ProfileSummaryService
                              ) {

  def profileDescription(): String = {

    val profile =
      profileValueService
        .buildProfile()

    val summary =
      profileSummaryService
        .summaryText()

    s"${profile.profileKey()}::$summary"
  }

}