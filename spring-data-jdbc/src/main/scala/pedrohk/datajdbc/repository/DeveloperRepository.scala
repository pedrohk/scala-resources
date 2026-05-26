package pedrohk.datajdbc.repository

import org.springframework.data.repository.CrudRepository
import pedrohk.datajdbc.model.Developer

trait DeveloperRepository extends CrudRepository[Developer, java.lang.Long] {

  def findBySpecialty(specialty: String): java.lang.Iterable[Developer]

  def findByActive(active: Boolean): java.lang.Iterable[Developer]
}