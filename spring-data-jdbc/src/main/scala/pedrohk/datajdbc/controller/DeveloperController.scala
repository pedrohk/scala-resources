package pedrohk.datajdbc.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import pedrohk.datajdbc.model.Developer
import pedrohk.datajdbc.service.DeveloperService

@RestController
@RequestMapping(Array("/developers"))
class DeveloperController(
                           developerService: DeveloperService
                         ) {

  @PostMapping
  def createDeveloper(
                       @RequestBody developer: Developer
                     ): Developer = {

    developerService.createDeveloper(developer)
  }

  @GetMapping(Array("/{identifier}"))
  def findDeveloper(
                     @PathVariable identifier: Long
                   ): Option[Developer] = {

    developerService.findDeveloperById(identifier)
  }

  @GetMapping(Array("/specialty/{specialty}"))
  def findBySpecialty(
                       @PathVariable specialty: String
                     ): List[Developer] = {

    developerService.findDevelopersBySpecialty(specialty)
  }

  @GetMapping(Array("/active"))
  def findActiveDevelopers(): List[Developer] = {

    developerService.findActiveDevelopers()
  }

  @DeleteMapping(Array("/{identifier}"))
  def deleteDeveloper(
                       @PathVariable identifier: Long
                     ): Unit = {

    developerService.removeDeveloper(identifier)
  }
}