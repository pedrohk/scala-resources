package pedrohk.injection.repository

import pedrohk.injection.model.DeveloperProfile

trait ProfileRepository {

  def findById(
                id: Long
              ): Option[DeveloperProfile]

}