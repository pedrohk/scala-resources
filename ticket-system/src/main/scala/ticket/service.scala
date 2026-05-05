package ticket

class TicketService(repo: TicketRepository) {

  def buyTickets(show: Show, seats: List[Seat]): List[Ticket] = {
    seats.flatMap { seat =>
      if (repo.reserve(show, seat)) {
        Some(Ticket(show.id, seat))
      } else {
        None
      }
    }
  }

  def remainingCapacity(show: Show): Int = {
    show.capacity - repo.totalReserved(show.id)
  }

  def isSeatAvailable(show: Show, seat: Seat): Boolean = {
    !repo.reservedSeats(show.id).contains(seat.number)
  }
}