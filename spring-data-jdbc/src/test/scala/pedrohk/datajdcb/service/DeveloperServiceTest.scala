package pedrohk.datajdbc.service

import java.util.Optional

import scala.jdk.CollectionConverters.*

import org.mockito.Mockito.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import pedrohk.datajdbc.model.Developer
import pedrohk.datajdbc.repository.DeveloperRepository

class DeveloperServiceTest
  extends AnyFlatSpec
    with Matchers {

  "DeveloperService" should "create developer successfully" in {

    val repository =
      mock(classOf[DeveloperRepository])

    val developer =
      Developer(
        1L,
        "Pedro Henrique",
        "Spring JDBC",
        8,
        true
      )

    when(
      repository.save(developer)
    ).thenReturn(developer)

    val service =
      new DeveloperService(repository)

    val result =
      service.createDeveloper(developer)

    result shouldBe developer

    verify(repository, times(1))
      .save(developer)
  }

  it should "find developer by identifier" in {

    val repository =
      mock(classOf[DeveloperRepository])

    val developer =
      Developer(
        5L,
        "Lia Martins",
        "Persistence",
        12,
        true
      )

    when(
      repository.findById(5L)
    ).thenReturn(
      Optional.of(developer)
    )

    val service =
      new DeveloperService(repository)

    val result =
      service.findDeveloperById(5L)

    result.isDefined shouldBe true
    result.get.fullName shouldBe "Lia Martins"
  }

  it should "return empty option when developer does not exist" in {

    val repository =
      mock(classOf[DeveloperRepository])

    when(
      repository.findById(100L)
    ).thenReturn(
      Optional.empty()
    )

    val service =
      new DeveloperService(repository)

    val result =
      service.findDeveloperById(100L)

    result shouldBe None
  }

  it should "find developers by specialty" in {

    val repository =
      mock(classOf[DeveloperRepository])

    val developers =
      List(
        Developer(
          10L,
          "Pedro Henrique",
          "Spring Data JDBC",
          9,
          true
        ),
        Developer(
          11L,
          "Lia Martins",
          "Spring Data JDBC",
          14,
          true
        )
      )

    when(
      repository.findBySpecialty(
        "Spring Data JDBC"
      )
    ).thenReturn(
      developers.asJava
    )

    val service =
      new DeveloperService(repository)

    val result =
      service.findDevelopersBySpecialty(
        "Spring Data JDBC"
      )

    result.size shouldBe 2
  }

  it should "return active developers only" in {

    val repository =
      mock(classOf[DeveloperRepository])

    val developers =
      List(
        Developer(
          21L,
          "Pedro Henrique",
          "Architecture",
          7,
          true
        )
      )

    when(
      repository.findByActive(true)
    ).thenReturn(
      developers.asJava
    )

    val service =
      new DeveloperService(repository)

    val result =
      service.findActiveDevelopers()

    result.forall(_.active) shouldBe true
  }

  it should "delete developer by identifier" in {

    val repository =
      mock(classOf[DeveloperRepository])

    val service =
      new DeveloperService(repository)

    service.removeDeveloper(99L)

    verify(repository, times(1))
      .deleteById(99L)
  }
}