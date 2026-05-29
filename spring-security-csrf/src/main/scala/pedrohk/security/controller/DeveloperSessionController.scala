package pedrohk.security.controller

  import org.springframework.http.ResponseEntity
  import org.springframework.web.bind.annotation.GetMapping
  import org.springframework.web.bind.annotation.PostMapping
  import org.springframework.web.bind.annotation.RequestBody
  import org.springframework.web.bind.annotation.RequestMapping
  import org.springframework.web.bind.annotation.RestController
  import pedrohk.security.model.DeveloperSession
  import pedrohk.security.service.DeveloperSessionService

  @RestController
  @RequestMapping(Array("/sessions"))
  class DeveloperSessionController(
                                    developerSessionService: DeveloperSessionService
                                  ) {

    @GetMapping
    def status(): ResponseEntity[String] = {

      ResponseEntity.ok(
        "CSRF protection enabled"
      )
    }

    @PostMapping
    def createSession(
                       @RequestBody developerSession: DeveloperSession
                     ): ResponseEntity[DeveloperSession] = {

      val createdSession =
        developerSessionService.buildSession(
          developerSession.owner,
          developerSession.activeProject
        )

      ResponseEntity.ok(createdSession)
    }
  }