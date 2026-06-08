package pedrohk.springvalue.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import pedrohk.springvalue.model.DeveloperProfile

@Service
class ProfileValueService(
                           @Value("${developer.owner}")
                           private val owner: String,

                           @Value("${developer.team}")
                           private val team: String,

                           @Value("${developer.mentor}")
                           private val mentor: String,

                           @Value("${developer.environment}")
                           private val environment: String
                         ) {

  def buildProfile(): DeveloperProfile = {
    new DeveloperProfile(
      owner,
      team,
      mentor,
      environment
    )
  }

  def ownerName(): String = owner

  def teamName(): String = team

  def mentorName(): String = mentor

  def environmentName(): String = environment

}