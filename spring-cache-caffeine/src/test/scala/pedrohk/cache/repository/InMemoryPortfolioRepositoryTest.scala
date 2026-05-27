package pedrohk.cache.repository

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import pedrohk.cache.model.Portfolio

class InMemoryPortfolioRepositoryTest
  extends AnyFlatSpec
    with Matchers {

  "InMemoryPortfolioRepository" should "find stored portfolio" in {

    val repository =
      new InMemoryPortfolioRepository

    val result =
      repository.findByIdentifier(1L)

    result.owner shouldBe "Pedro Henrique"
  }

  it should "save portfolio correctly" in {

    val repository =
      new InMemoryPortfolioRepository

    val portfolio =
      Portfolio(
        9L,
        "Lia Martins",
        "Cache",
        19
      )

    val saved =
      repository.save(portfolio)

    saved shouldBe portfolio

    repository
      .findByIdentifier(9L)
      .technology shouldBe "Cache"
  }

  it should "store multiple portfolios" in {

    val repository =
      new InMemoryPortfolioRepository

    val first =
      Portfolio(
        30L,
        "Pedro Henrique",
        "Caffeine",
        5
      )

    val second =
      Portfolio(
        31L,
        "Lia Martins",
        "Spring Cache",
        7
      )

    repository.save(first)
    repository.save(second)

    repository
      .findByIdentifier(30L)
      .owner shouldBe "Pedro Henrique"

    repository
      .findByIdentifier(31L)
      .owner shouldBe "Lia Martins"
  }
}