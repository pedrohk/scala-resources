package pedrohk.testingwebclient.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import pedrohk.testingwebclient.model.Profile
import pedrohk.testingwebclient.model.ProfileSummary
import pedrohk.testingwebclient.service.ProfileService

@RestController
@RequestMapping(Array("/profiles"))
class ProfileController(
                         service: ProfileService
                       ) {

  @GetMapping(Array("/current"))
  def current(): Profile = {
    service.profile()
  }

  @GetMapping(Array("/summary"))
  def summary(): ProfileSummary = {
    service.summary()
  }

}