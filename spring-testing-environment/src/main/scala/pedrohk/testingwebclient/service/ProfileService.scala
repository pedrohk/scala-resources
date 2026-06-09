package pedrohk.testingwebclient.service

import org.springframework.stereotype.Service
import pedrohk.testingwebclient.model.Profile
import pedrohk.testingwebclient.model.ProfileSummary

@Service
class ProfileService(
                      environmentService: EnvironmentService
                    ) {

  def profile(): Profile = {
    Profile(
      10L,
      "Pedro Henrique",
      environmentService.currentEnvironment(),
      true
    )
  }

  def summary(): ProfileSummary = {
    ProfileSummary(
      s"${profile().owner}-${profile().environment}"
    )
  }

}