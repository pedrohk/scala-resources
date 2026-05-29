package pedrohk.security.service

  import org.springframework.stereotype.Service
  import pedrohk.security.model.DeveloperSession

  @Service
  class DeveloperSessionService {

    def buildSession(
                      owner: String,
                      activeProject: String
                    ): DeveloperSession = {

      new DeveloperSession(
        owner,
        activeProject
      )
    }

    def validateProject(
                         project: String
                       ): Boolean = {

      project.nonEmpty &&
        project.length >= 4
    }
  }
