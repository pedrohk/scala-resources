package calendar

import org.scalatest.funsuite.AnyFunSuite

import java.time.LocalDateTime

class CalendarServiceSpec extends AnyFunSuite {

  def newService(): CalendarService = {
    new CalendarService(new MeetingRepository)
  }

  def baseDate(): LocalDateTime = {
    LocalDateTime.of(2025, 1, 10, 0, 0)
  }

  test("book meeting successfully") {
    val service = newService()

    val meeting = service.bookMeeting(
      "john",
      "sync",
      baseDate().withHour(10),
      baseDate().withHour(11)
    )

    assert(meeting.owner == "john")
    assert(service.totalMeetings() == 1)
  }

  test("cannot book overlapping meetings") {
    val service = newService()

    service.bookMeeting(
      "john",
      "a",
      baseDate().withHour(10),
      baseDate().withHour(11)
    )

    assertThrows[IllegalArgumentException] {
      service.bookMeeting(
        "john",
        "b",
        baseDate().withHour(10).withMinute(30),
        baseDate().withHour(11).withMinute(30)
      )
    }
  }

  test("different users can overlap") {
    val service = newService()

    service.bookMeeting(
      "john",
      "a",
      baseDate().withHour(10),
      baseDate().withHour(11)
    )

    val second = service.bookMeeting(
      "mary",
      "b",
      baseDate().withHour(10),
      baseDate().withHour(11)
    )

    assert(second.owner == "mary")
    assert(service.totalMeetings() == 2)
  }

  test("remove meeting works") {
    val service = newService()

    val meeting = service.bookMeeting(
      "john",
      "x",
      baseDate().withHour(9),
      baseDate().withHour(10)
    )

    val removed = service.removeMeeting(meeting.id)

    assert(removed)
    assert(service.totalMeetings() == 0)
  }

  test("remove invalid meeting returns false") {
    val service = newService()

    val removed = service.removeMeeting("invalid")

    assert(!removed)
  }

  test("list meetings ordered by time") {
    val service = newService()

    service.bookMeeting(
      "john",
      "late",
      baseDate().withHour(15),
      baseDate().withHour(16)
    )

    service.bookMeeting(
      "john",
      "early",
      baseDate().withHour(9),
      baseDate().withHour(10)
    )

    val meetings = service.listMeetings("john")

    assert(meetings.head.title == "early")
    assert(meetings.last.title == "late")
  }

  test("suggest best time for two people") {
    val service = newService()

    service.bookMeeting(
      "john",
      "busy",
      baseDate().withHour(8),
      baseDate().withHour(10)
    )

    service.bookMeeting(
      "mary",
      "busy",
      baseDate().withHour(10),
      baseDate().withHour(12)
    )

    val suggestion = service.suggestBestTime(
      "john",
      "mary",
      baseDate(),
      60
    )

    assert(suggestion.isDefined)
    assert(suggestion.get.getHour == 12)
  }

  test("suggestion returns none when fully busy") {
    val service = newService()

    service.bookMeeting(
      "john",
      "all-day",
      baseDate().withHour(8),
      baseDate().withHour(18)
    )

    val result = service.suggestBestTime(
      "john",
      "mary",
      baseDate(),
      30
    )

    assert(result.isEmpty)
  }

  test("invalid meeting range throws") {
    val service = newService()

    assertThrows[IllegalArgumentException] {
      service.bookMeeting(
        "john",
        "bad",
        baseDate().withHour(12),
        baseDate().withHour(11)
      )
    }
  }

  test("high volume meetings") {
    val service = newService()

    (0 until 100).foreach { i =>
      service.bookMeeting(
        s"user-$i",
        s"meeting-$i",
        baseDate().withHour(8),
        baseDate().withHour(9)
      )
    }

    assert(service.totalMeetings() == 100)
  }

  test("meeting edge touching does not conflict") {
    val service = newService()

    service.bookMeeting(
      "john",
      "first",
      baseDate().withHour(10),
      baseDate().withHour(11)
    )

    val second = service.bookMeeting(
      "john",
      "second",
      baseDate().withHour(11),
      baseDate().withHour(12)
    )

    assert(second.title == "second")
  }

  test("consistency multiple listings") {
    val service = newService()

    service.bookMeeting(
      "john",
      "x",
      baseDate().withHour(9),
      baseDate().withHour(10)
    )

    val first = service.listMeetings("john")
    val second = service.listMeetings("john")

    assert(first == second)
  }
}