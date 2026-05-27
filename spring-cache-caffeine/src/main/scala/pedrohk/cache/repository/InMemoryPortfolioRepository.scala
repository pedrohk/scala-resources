package pedrohk.cache.repository

import java.util.concurrent.ConcurrentHashMap
import org.springframework.stereotype.Repository
import pedrohk.cache.model.Portfolio

@Repository
class InMemoryPortfolioRepository
  extends PortfolioRepository {

  private val storage =
    new ConcurrentHashMap[Long, Portfolio]()

  storage.put(
    1L,
    Portfolio(
      1L,
      "Pedro Henrique",
      "Spring Boot",
      24
    )
  )

  storage.put(
    2L,
    Portfolio(
      2L,
      "Lia Martins",
      "Caching",
      17
    )
  )

  override def findByIdentifier(
                                 identifier: Long
                               ): Portfolio = {

    storage.get(identifier)
  }

  override def save(
                     portfolio: Portfolio
                   ): Portfolio = {

    storage.put(
      portfolio.identifier,
      portfolio
    )

    portfolio
  }
}