package social

import org.scalatest.funsuite.AnyFunSuite

class TimelineServiceSpec extends AnyFunSuite {

  def buildService(): TimelineService = {
    new TimelineService(new PhotoRepository())
  }

  val user1 = User("u1", "alice")
  val user2 = User("u2", "bob")

  test("publish photo") {
    val service = buildService()

    val photo = service.publishPhoto(
      "p1",
      user1,
      "http://image",
      "vacation"
    )

    assert(photo.id == "p1")
    assert(photo.owner == user1)
    assert(photo.description == "vacation")
  }

  test("cannot publish duplicate photo") {
    val service = buildService()

    service.publishPhoto(
      "p1",
      user1,
      "http://image",
      "vacation"
    )

    assertThrows[IllegalArgumentException] {
      service.publishPhoto(
        "p1",
        user1,
        "http://image2",
        "duplicate"
      )
    }
  }

  test("tag photo") {
    val service = buildService()

    service.publishPhoto(
      "p1",
      user1,
      "http://image",
      "photo"
    )

    val updated = service.tagPhoto(
      "p1",
      Tag("travel")
    )

    assert(updated.tags.contains(Tag("travel")))
  }

  test("multiple tags") {
    val service = buildService()

    service.publishPhoto(
      "p1",
      user1,
      "http://image",
      "photo"
    )

    service.tagPhoto("p1", Tag("travel"))
    val updated = service.tagPhoto("p1", Tag("summer"))

    assert(updated.tags.size == 2)
  }

  test("comment photo") {
    val service = buildService()

    service.publishPhoto(
      "p1",
      user1,
      "http://image",
      "photo"
    )

    val updated = service.commentPhoto(
      "p1",
      "c1",
      user2,
      "nice picture"
    )

    assert(updated.comments.size == 1)
    assert(updated.comments.head.message == "nice picture")
  }

  test("multiple comments preserve order") {
    val service = buildService()

    service.publishPhoto(
      "p1",
      user1,
      "http://image",
      "photo"
    )

    service.commentPhoto("p1", "c1", user2, "first")
    val updated = service.commentPhoto("p1", "c2", user1, "second")

    assert(updated.comments.map(_.message) == List("first", "second"))
  }

  test("timeline sorted newest first") {
    val service = buildService()

    val p1 = service.publishPhoto(
      "p1",
      user1,
      "http://1",
      "one"
    )

    Thread.sleep(2)

    val p2 = service.publishPhoto(
      "p2",
      user2,
      "http://2",
      "two"
    )

    val timeline = service.timeline()

    assert(timeline.head.id == p2.id)
    assert(timeline.last.id == p1.id)
  }

  test("delete photo") {
    val service = buildService()

    service.publishPhoto(
      "p1",
      user1,
      "http://image",
      "photo"
    )

    val deleted = service.deletePhoto("p1")

    assert(deleted)
    assert(service.getPhoto("p1").isEmpty)
  }

  test("delete missing photo returns false") {
    val service = buildService()

    assert(!service.deletePhoto("missing"))
  }

  test("empty timeline") {
    val service = buildService()

    assert(service.timeline().isEmpty)
  }

  test("cannot comment missing photo") {
    val service = buildService()

    assertThrows[IllegalArgumentException] {
      service.commentPhoto(
        "missing",
        "c1",
        user1,
        "hello"
      )
    }
  }

  test("cannot tag missing photo") {
    val service = buildService()

    assertThrows[IllegalArgumentException] {
      service.tagPhoto(
        "missing",
        Tag("travel")
      )
    }
  }

  test("empty message comment is invalid") {
    val service = buildService()

    service.publishPhoto(
      "p1",
      user1,
      "http://image",
      "photo"
    )

    assertThrows[IllegalArgumentException] {
      service.commentPhoto(
        "p1",
        "c1",
        user1,
        ""
      )
    }
  }

  test("empty photo id is invalid") {
    val service = buildService()

    assertThrows[IllegalArgumentException] {
      service.publishPhoto(
        "",
        user1,
        "http://image",
        "photo"
      )
    }
  }

  test("empty url is invalid") {
    val service = buildService()

    assertThrows[IllegalArgumentException] {
      service.publishPhoto(
        "p1",
        user1,
        "",
        "photo"
      )
    }
  }

  test("repository count") {
    val repository = new PhotoRepository()

    repository.save(
      Photo(
        "p1",
        user1,
        "http://image",
        "photo"
      )
    )

    repository.save(
      Photo(
        "p2",
        user2,
        "http://image2",
        "photo2"
      )
    )

    assert(repository.count() == 2)
  }

  test("high volume publishing") {
    val service = buildService()

    (1 to 500).foreach { i =>
      service.publishPhoto(
        s"p$i",
        user1,
        s"http://$i",
        s"photo-$i"
      )
    }

    assert(service.timeline().size == 500)
  }

  test("photo retrieval") {
    val service = buildService()

    service.publishPhoto(
      "p1",
      user1,
      "http://image",
      "photo"
    )

    val result = service.getPhoto("p1")

    assert(result.isDefined)
    assert(result.get.id == "p1")
  }
}