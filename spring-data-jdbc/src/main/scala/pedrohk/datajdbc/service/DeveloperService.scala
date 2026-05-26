package pedrohk.datajdbc.service

import org.springframework.stereotype.Service
import pedrohk.datajdbc.model.Developer
import pedrohk.datajdbc.repository.DeveloperRepository

import scala.jdk.CollectionConverters.*

@Service
class DeveloperService(
                        developerRepository: DeveloperRepository
                      ) {

  def createDeveloper(developer: Developer): Developer = {
    developerRepository.save(developer)
  }

  def findDeveloperById(identifier: Long): Option[Developer] = {
    Option(
      developerRepository.findById(identifier).orElse(null)
    )
  }

  def findDevelopersBySpecialty(specialty: String): List[Developer] = {
    developerRepository
      .findBySpecialty(specialty)
      .asScala
      .toList
  }

  def findActiveDevelopers(): List[Developer] = {
    developerRepository
      .findByActive(true)
      .asScala
      .toList
  }

  def removeDeveloper(identifier: Long): Unit = {
    developerRepository.deleteById(identifier)
  }
}