package pedrohk.datajdbc.controller

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import pedrohk.datajdbc.model.Developer
import pedrohk.datajdbc.service.DeveloperService

class FakeDeveloperService
  extends DeveloperService(null) {

  override def createDeveloper(
                                developer: Developer
                              ): Developer = {

    developer
  }

  override def findDeveloperById(
                                  identifier: Long
                                ): Option[Developer] = {

    Some(
      Developer(
        identifier,
        "Pedro Henrique",
        "Spring Boot",
        10,
        true
      )
    )
  }

  override def findDevelopersBySpecialty(
                                          specialty: String
                                        ): List[Developer] = {

    List(
      Developer(
        7L,
        "Lia Martins",
        specialty,
        15,
        true
      )
    )
  }

  override def findActiveDevelopers(): List[Developer] = {

    List(
      Developer(
        30L,
        "Pedro Henrique",
        "Data JDBC",
        9,
        true
      )
    )
  }

  override def removeDeveloper(
                                identifier: Long
                              ): Unit = {}
}

class DeveloperControllerTest
  extends AnyFlatSpec
    with Matchers {

  "DeveloperController" should "create developer" in {

    val controller =
      new DeveloperController(
        new FakeDeveloperService
      )

    val developer =
      Developer(
        1L,
        "Pedro Henrique",
        "Spring JDBC",
        8,
        true
      )

    val result =
      controller.createDeveloper(
        developer
      )

    result shouldBe developer
  }

  it should "find developer by identifier" in {

    val controller =
      new DeveloperController(
        new FakeDeveloperService
      )

    val result =
      controller.findDeveloper(9L)

    result.isDefined shouldBe true
    result.get.fullName shouldBe "Pedro Henrique"
  }

  it should "find developers by specialty" in {

    val controller =
      new DeveloperController(
        new FakeDeveloperService
      )

    val result =
      controller.findBySpecialty(
        "Persistence"
      )

    result.head.specialty shouldBe "Persistence"
  }

  it should "return active developers" in {

    val controller =
      new DeveloperController(
        new FakeDeveloperService
      )

    val result =
      controller.findActiveDevelopers()

    result.nonEmpty shouldBe true
    result.head.active shouldBe true
  }

  it should "delete developer successfully" in {

    val controller =
      new DeveloperController(
        new FakeDeveloperService
      )

    noException shouldBe thrownBy {
      controller.deleteDeveloper(77L)
    }
  }
}