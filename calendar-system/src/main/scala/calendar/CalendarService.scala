package calendar

import java.time.LocalDateTime
import java.util.UUID

class CalendarService(repo: MeetingRepository) {

  def bookMeeting(
                   owner: String,
                   title: String,
                   start: LocalDateTime,
                   end: LocalDateTime
                 ): Meeting = {

    require(end.isAfter(start), "End must be after start")

    val conflicts = repo.byOwner(owner).exists { existing =>
      overlaps(existing.start, existing.end, start, end)
    }

    if (conflicts) {
      throw new IllegalArgumentException("Meeting conflict detected")
    }

    val meeting = Meeting(
      UUID.randomUUID().toString,
      owner,
      title,
      start,
      end
    )

    repo.save(meeting)
    meeting
  }

  def removeMeeting(id: String): Boolean = {
    repo.remove(id).isDefined
  }

  def listMeetings(owner: String): List[Meeting] = {
    repo.byOwner(owner).sortBy(_.start)
  }

  def suggestBestTime(
                       personA: String,
                       personB: String,
                       day: LocalDateTime,
                       durationMinutes: Long
                     ): Option[LocalDateTime] = {

    val startOfDay = day.withHour(8).withMinute(0).withSecond(0).withNano(0)
    val endOfDay = day.withHour(18).withMinute(0).withSecond(0).withNano(0)

    var current = startOfDay

    while (!current.plusMinutes(durationMinutes).isAfter(endOfDay)) {

      val candidateEnd = current.plusMinutes(durationMinutes)

      val aBusy = repo.byOwner(personA).exists { meeting =>
        overlaps(meeting.start, meeting.end, current, candidateEnd)
      }

      val bBusy = repo.byOwner(personB).exists { meeting =>
        overlaps(meeting.start, meeting.end, current, candidateEnd)
      }

      if (!aBusy && !bBusy) {
        return Some(current)
      }

      current = current.plusMinutes(30)
    }

    None
  }

  private def overlaps(
                        aStart: LocalDateTime,
                        aEnd: LocalDateTime,
                        bStart: LocalDateTime,
                        bEnd: LocalDateTime
                      ): Boolean = {

    aStart.isBefore(bEnd) && bStart.isBefore(aEnd)
  }

  def totalMeetings(): Int = {
    repo.size
  }
}