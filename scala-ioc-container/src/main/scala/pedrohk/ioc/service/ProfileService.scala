package pedrohk.ioc.service

import pedrohk.ioc.repository.ProfileRepository

class ProfileService(
                      private val repository: ProfileRepository
                    ) {

  def profileName(): String = {
    repository.loadName()
  }

  def greeting(): String = {
    s"Welcome ${repository.loadName()}"
  }

}