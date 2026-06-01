package pedrohk.security.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{GetMapping, RestController}
import pedrohk.security.model.DeveloperProfile
import pedrohk.security.service.DeveloperProfileService

@RestController
class DeveloperPortalController(
                                 developerProfileService: DeveloperProfileService
                               ) {

  @GetMapping(Array("/public/status"))
  def status(): ResponseEntity[String] = {

    ResponseEntity.ok(
      "Authorization configured"
    )
  }

  @GetMapping(Array("/portal/profile"))
  def profile(): ResponseEntity[DeveloperProfile] = {

    ResponseEntity.ok(
      developerProfileService
        .loadPedroHenriqueProfile()
    )
  }

  @GetMapping(Array("/portal/lia"))
  def liaProfile(): ResponseEntity[DeveloperProfile] = {

    ResponseEntity.ok(
      developerProfileService
        .loadLiaProfile()
    )
  }
}