package calendar

import scala.collection.mutable

class MeetingRepository {

  private val meetings: mutable.Map[String, Meeting] = mutable.Map.empty

  def save(meeting: Meeting): Unit = {
    meetings.put(meeting.id, meeting)
  }

  def remove(id: String): Option[Meeting] = {
    meetings.remove(id)
  }

  def find(id: String): Option[Meeting] = {
    meetings.get(id)
  }

  def all(): List[Meeting] = {
    meetings.values.toList
  }

  def byOwner(owner: String): List[Meeting] = {
    meetings.values.filter(_.owner == owner).toList
  }

  def size: Int = {
    meetings.size
  }
}