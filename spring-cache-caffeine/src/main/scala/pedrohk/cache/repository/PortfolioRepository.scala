package pedrohk.cache.repository

import pedrohk.cache.model.Portfolio

trait PortfolioRepository {

  def findByIdentifier(
                        identifier: Long
                      ): Portfolio

  def save(
            portfolio: Portfolio
          ): Portfolio
}