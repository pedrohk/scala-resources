package ticket

import java.time.LocalDate

enum Zone {
  case VIP, REGULAR, ECONOMY
}

case class Seat(number: Int, zone: Zone)

case class Show(
                 id: String,
                 name: String,
                 date: LocalDate,
                 capacity: Int
               )

case class Ticket(
                   showId: String,
                   seat: Seat
                 )