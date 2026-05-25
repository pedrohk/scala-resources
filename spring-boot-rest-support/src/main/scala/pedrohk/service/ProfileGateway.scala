package pedrohk.service


import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestOperations
import pedrohk.model.ProfileResponse

@Service
class ProfileGateway(
                      restTemplate: RestOperations,
                      @Value("${profile.api.base-url:https://profiles.internal}") baseUrl: String
                    ) {

  def fetchProfile(identifier: Long): ProfileResponse = {
    val response = restTemplate.exchange(
      s"$baseUrl/api/profiles/$identifier",
      HttpMethod.GET,
      null,
      classOf[ProfileResponse]
    )

    response.getBody
  }
}