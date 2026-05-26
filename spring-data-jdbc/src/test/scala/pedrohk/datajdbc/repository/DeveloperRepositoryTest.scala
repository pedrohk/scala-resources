package pedrohk.datajdbc.repository

import scala.jdk.CollectionConverters.*

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import pedrohk.datajdbc.model.Developer

class DeveloperRepositoryTest
  extends AnyFlatSpec
    with Matchers {

  "DeveloperRepository" should "support repository operations" in {

    class RepositoryInstance
      extends DeveloperRepository {

      override def findBySpecialty(
                                    specialty: String
                                  ) = {
        List.empty[Developer].asJava
      }

      override def findByActive(
                                 active: Boolean
                               ) = {
        List.empty[Developer].asJava
      }

      override def save[S <: Developer](
                                         entity: S
                                       ): S = entity

      override def saveAll[S <: Developer](
                                            entities: java.lang.Iterable[S]
                                          ) = entities

      override def findById(
                             id: java.lang.Long
                           ) = java.util.Optional.empty[Developer]()

      override def existsById(
                               id: java.lang.Long
                             ) = false

      override def findAll() = {
        List.empty[Developer].asJava
      }

      override def findAllById(
                                ids: java.lang.Iterable[java.lang.Long]
                              ) = {
        List.empty[Developer].asJava
      }

      override def count() = 0L

      override def deleteById(
                               id: java.lang.Long
                             ): Unit = {}

      override def delete(
                           entity: Developer
                         ): Unit = {}

      override def deleteAllById(
                                  ids: java.lang.Iterable[_ <: java.lang.Long]
                                ): Unit = {}

      override def deleteAll(
                              entities: java.lang.Iterable[_ <: Developer]
                            ): Unit = {}

      override def deleteAll(): Unit = {}
    }

    val repository =
      new RepositoryInstance

    repository shouldBe a[DeveloperRepository]
  }
}