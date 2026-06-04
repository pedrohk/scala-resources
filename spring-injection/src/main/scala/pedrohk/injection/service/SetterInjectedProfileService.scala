package pedrohk.injection.service

import pedrohk.injection.model.DeveloperProfile
import pedrohk.injection.repository.ProfileRepository

class SetterInjectedProfileService {

  private var repository:
    ProfileRepository = _

  def setRepository(
                     repository: ProfileRepository
                   ): Unit = {

    this.repository =
      repository
  }

  def load(
            id: Long
          ): Option[DeveloperProfile] = {

    repository.findById(
      id
    )
  }

  def mentor(
              id: Long
            ): Option[String] = {

    load(id)
      .map(_.mentor)
  }

}