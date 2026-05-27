package pedrohk.cache.service

import org.mockito.Mockito.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import pedrohk.cache.model.Portfolio
import pedrohk.cache.repository.PortfolioRepository

class PortfolioServiceTest
  extends AnyFlatSpec
    with Matchers {

  "PortfolioService" should "find portfolio by identifier" in {

    val repository =
      mock(classOf[PortfolioRepository])

    val portfolio =
      Portfolio(
        1L,
        "Pedro Henrique",
        "Spring",
        22
      )

    when(
      repository.findByIdentifier(1L)
    ).thenReturn(portfolio)

    val service =
      new PortfolioService(repository)

    val result =
      service.findPortfolio(1L)

    result shouldBe portfolio

    verify(repository, times(1))
      .findByIdentifier(1L)
  }

  it should "update portfolio successfully" in {

    val repository =
      mock(classOf[PortfolioRepository])

    val portfolio =
      Portfolio(
        2L,
        "Lia Martins",
        "Caffeine",
        14
      )

    when(
      repository.save(portfolio)
    ).thenReturn(portfolio)

    val service =
      new PortfolioService(repository)

    val result =
      service.updatePortfolio(portfolio)

    result shouldBe portfolio

    verify(repository, times(1))
      .save(portfolio)
  }

  it should "return different portfolios correctly" in {

    val repository =
      mock(classOf[PortfolioRepository])

    val first =
      Portfolio(
        10L,
        "Pedro Henrique",
        "Spring Boot",
        11
      )

    val second =
      Portfolio(
        20L,
        "Lia Martins",
        "Redis",
        8
      )

    when(
      repository.findByIdentifier(10L)
    ).thenReturn(first)

    when(
      repository.findByIdentifier(20L)
    ).thenReturn(second)

    val service =
      new PortfolioService(repository)

    service.findPortfolio(10L)
      .owner shouldBe "Pedro Henrique"

    service.findPortfolio(20L)
      .owner shouldBe "Lia Martins"
  }
}