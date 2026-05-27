package pedrohk.cache.service

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import pedrohk.cache.model.Portfolio
import pedrohk.cache.repository.PortfolioRepository

@Service
class PortfolioService(
                        portfolioRepository: PortfolioRepository
                      ) {

  @Cacheable(Array("portfolio-cache"))
  def findPortfolio(
                     identifier: Long
                   ): Portfolio = {

    portfolioRepository.findByIdentifier(
      identifier
    )
  }

  @CacheEvict(
    value = Array("portfolio-cache"),
    key = "#portfolio.identifier"
  )
  def updatePortfolio(
                       portfolio: Portfolio
                     ): Portfolio = {

    portfolioRepository.save(portfolio)
  }
}