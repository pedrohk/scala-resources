package pedrohk.ioc.service

import pedrohk.ioc.model.PlatformTeam

class TeamService(
                   private val platformTeam: PlatformTeam
                 ) {

  def teamLabel(): String = {
    platformTeam.teamName
  }

}