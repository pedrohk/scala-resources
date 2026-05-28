package pedrohk.jpa.repository

import java.util.Optional

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import pedrohk.jpa.model.DeveloperAccount

@Repository
trait DeveloperAccountRepository
  extends JpaRepository[
    DeveloperAccount,
    java.lang.Long
  ] {

  def findByOwner(
                   owner: String
                 ): Optional[DeveloperAccount]
}