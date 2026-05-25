package pedrohk.controller


import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}
import pedrohk.model.Profile
import pedrohk.service.ProfileService

@RestController
@RequestMapping(Array("/profiles"))
class ProfileController(profileService: ProfileService) {

  @GetMapping(Array("/{identifier}"))
  def findProfile(@PathVariable identifier: Long): Profile = {
    profileService.retrieveProfile(identifier)
  }
}