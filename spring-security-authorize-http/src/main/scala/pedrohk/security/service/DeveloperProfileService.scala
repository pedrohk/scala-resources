package pedrohk.security.service

import org.springframework.stereotype.Service
import pedrohk.security.model.DeveloperProfile

@Service
class DeveloperProfileService {

  def loadPedroHenriqueProfile(): DeveloperProfile = {

    new DeveloperProfile(
      "Pedro Henrique",
      "Spring Security",
      8
    )
  }

  def loadLiaProfile(): DeveloperProfile = {

    new DeveloperProfile(
      "Lia",
      "Platform Governance",
      4
    )
  }

  def validateAccess(
                      profile: DeveloperProfile
                    ): Boolean = {

    profile != null &&
      profile.owner.nonEmpty &&
      profile.activeProjects >= 0
  }

  def totalProjects(
                     primary: DeveloperProfile,
                     secondary: DeveloperProfile
                   ): Int = {

    primary.activeProjects +
      secondary.activeProjects
  }
}