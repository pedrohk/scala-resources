package pedrohk.ioc.repository

class InMemoryProfileRepository extends ProfileRepository {

  override def loadName(): String = {
    "Pedro Henrique"
  }

}