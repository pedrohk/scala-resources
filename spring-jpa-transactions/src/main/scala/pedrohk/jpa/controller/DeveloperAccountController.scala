package pedrohk.jpa.controller

import scala.jdk.OptionConverters.*

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import pedrohk.jpa.model.DeveloperAccount
import pedrohk.jpa.service.DeveloperAccountService

@RestController
@RequestMapping(Array("/accounts"))
class DeveloperAccountController(
                                  developerAccountService: DeveloperAccountService
                                ) {

  @GetMapping(Array("/{owner}"))
  def findAccount(
                   @PathVariable owner: String
                 ): DeveloperAccount = {

    developerAccountService
      .findByOwner(owner)
      .orNull
  }
}