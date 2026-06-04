package pedrohk.injection.repository

import pedrohk.injection.model.DeveloperProfile
import scala.jdk.CollectionConverters.*

class InMemoryProfileRepository
  extends ProfileRepository {

  private val profiles =
    List(
      new DeveloperProfile(
        1L,
        "Pedro Henrique",
        "Lia"
      ),
      new DeveloperProfile(
        2L,
        "Caio Ventura",
        "Lia"
      )
    )

  override def findById(
                         id: Long
                       ): Option[DeveloperProfile] = {

    profiles.find(
      _.id == id
    )
  }

}