package pedrohk.jpa.service

import scala.jdk.OptionConverters.*
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import pedrohk.jpa.model.DeveloperAccount
import pedrohk.jpa.repository.DeveloperAccountRepository

@Service
class DeveloperAccountService(
                               developerAccountRepository: DeveloperAccountRepository
                             ) {

  @Transactional
  def createAccount(
                     owner: String,
                     stack: String,
                     credits: Int
                   ): DeveloperAccount = {

    val account =
      new DeveloperAccount(
        owner,
        stack,
        credits
      )

    developerAccountRepository.save(account)
  }

  @Transactional
  def transferCredits(
                       source: DeveloperAccount,
                       destination: DeveloperAccount,
                       amount: Int
                     ): Unit = {

    if (source.credits < amount) {
      throw new IllegalArgumentException(
        "Insufficient credits"
      )
    }

    source.credits =
      source.credits - amount

    destination.credits =
      destination.credits + amount

    developerAccountRepository.save(source)
    developerAccountRepository.save(destination)
  }

  def findByOwner(
                   owner: String
                 ): Option[DeveloperAccount] = {

    developerAccountRepository
      .findByOwner(owner)
      .toScala
  }
}