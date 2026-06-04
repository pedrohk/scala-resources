package pedrohk.injection.service

import pedrohk.injection.model.DeveloperProfile
import pedrohk.injection.repository.ProfileRepository

class ConstructorInjectedProfileService(
                                         repository: ProfileRepository
                                       ) {

  def load(
            id: Long
          ): Option[DeveloperProfile] = {

    repository.findById(
      id
    )
  }

  def exists(
              id: Long
            ): Boolean = {

    load(id).isDefined
  }

}