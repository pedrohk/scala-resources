package pedrohk.testingwebclient.service

import org.springframework.core.env.Environment
import org.springframework.stereotype.Service

@Service
class EnvironmentService(
                          environment: Environment
                        ) {

  def currentEnvironment(): String = {
    Option(environment.getProperty("application.environment"))
      .getOrElse("local")
  }

}