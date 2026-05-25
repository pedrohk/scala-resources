package pedrohk.service


import org.springframework.stereotype.Service
import pedrohk.model.{Profile, ProfileResponse}

@Service
class ProfileService(profileGateway: ProfileGateway) {

  def retrieveProfile(identifier: Long): Profile = {
    val response = profileGateway.fetchProfile(identifier)
    mapResponse(response)
  }

  private def mapResponse(response: ProfileResponse): Profile = {
    Profile(
      id = response.identifier,
      owner = response.displayName,
      expertise = response.specialty,
      active = response.enabled
    )
  }
}