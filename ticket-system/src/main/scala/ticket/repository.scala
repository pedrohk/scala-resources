package ticket

import scala.collection.mutable

class TicketRepository {

  private val tickets: mutable.Map[String, mutable.Set[Int]] = mutable.Map.empty

  def reserve(show: Show, seat: Seat): Boolean = {
    val reserved = tickets.getOrElseUpdate(show.id, mutable.Set.empty)
    if (reserved.size >= show.capacity) {
      false
    } else if (reserved.contains(seat.number)) {
      false
    } else {
      reserved.add(seat.number)
      true
    }
  }

  def reservedSeats(showId: String): Set[Int] = {
    tickets.getOrElse(showId, mutable.Set.empty).toSet
  }

  def totalReserved(showId: String): Int = {
    tickets.getOrElse(showId, mutable.Set.empty).size
  }
}