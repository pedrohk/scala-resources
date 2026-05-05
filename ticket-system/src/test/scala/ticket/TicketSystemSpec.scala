package ticket

import org.scalatest.funsuite.AnyFunSuite
import java.time.LocalDate

class TicketSystemSpec extends AnyFunSuite {

  def newService(): (Show, TicketRepository, TicketService) = {
    val show = Show("1", "Concert", LocalDate.now(), 5)
    val repo = new TicketRepository
    val service = new TicketService(repo)
    (show, repo, service)
  }

  test("buy single ticket") {
    val (show, repo, service) = newService()

    val seat = Seat(1, Zone.VIP)
    val tickets = service.buyTickets(show, List(seat))

    assert(tickets.size == 1)
    assert(repo.totalReserved(show.id) == 1)
  }

  test("cannot double book seat") {
    val (show, _, service) = newService()

    val seat = Seat(2, Zone.VIP)
    service.buyTickets(show, List(seat))
    val second = service.buyTickets(show, List(seat))

    assert(second.isEmpty)
  }

  test("multiple seats booking") {
    val (show, _, service) = newService()

    val seats = List(Seat(3, Zone.REGULAR), Seat(4, Zone.REGULAR))
    val tickets = service.buyTickets(show, seats)

    assert(tickets.size == 2)
  }

  test("respect capacity") {
    val (show, repo, service) = newService()

    val seats = List(
      Seat(10, Zone.ECONOMY),
      Seat(11, Zone.ECONOMY),
      Seat(12, Zone.ECONOMY),
      Seat(13, Zone.ECONOMY),
      Seat(14, Zone.ECONOMY),
      Seat(15, Zone.ECONOMY)
    )

    service.buyTickets(show, seats)

    assert(repo.totalReserved(show.id) == show.capacity)
  }

  test("remaining capacity decreases") {
    val (show, _, service) = newService()

    val before = service.remainingCapacity(show)
    service.buyTickets(show, List(Seat(20, Zone.VIP)))
    val after = service.remainingCapacity(show)

    assert(after == before - 1)
  }

  test("seat availability") {
    val (show, _, service) = newService()

    val seat = Seat(30, Zone.VIP)

    assert(service.isSeatAvailable(show, seat))

    service.buyTickets(show, List(seat))

    assert(!service.isSeatAvailable(show, seat))
  }

  test("high volume booking") {
    val bigShow = Show("2", "Festival", LocalDate.now(), 1000)
    val repo = new TicketRepository
    val service = new TicketService(repo)

    val seats = (1 to 1000).map(i => Seat(i, Zone.REGULAR)).toList
    val tickets = service.buyTickets(bigShow, seats)

    assert(tickets.size == 1000)
    assert(repo.totalReserved(bigShow.id) == 1000)
  }

  test("over capacity booking rejected") {
    val smallShow = Show("3", "Small", LocalDate.now(), 2)
    val repo = new TicketRepository
    val service = new TicketService(repo)

    val seats = List(Seat(1, Zone.VIP), Seat(2, Zone.VIP), Seat(3, Zone.VIP))
    val tickets = service.buyTickets(smallShow, seats)

    assert(tickets.size == 2)
  }

  test("reserved seats tracking") {
    val (show, repo, service) = newService()

    val seat = Seat(50, Zone.REGULAR)
    service.buyTickets(show, List(seat))

    val reserved = repo.reservedSeats(show.id)
    assert(reserved.contains(50))
  }

  test("consistency multiple runs") {
    val (show, _, service) = newService()

    val seat = Seat(60, Zone.VIP)
    val r1 = service.buyTickets(show, List(seat)).size
    val r2 = service.buyTickets(show, List(seat)).size

    assert(r1 == 1)
    assert(r2 == 0)
  }
}