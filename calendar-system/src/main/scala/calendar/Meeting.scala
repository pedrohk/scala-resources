package calendar

import java.time.LocalDateTime

case class Meeting(
                    id: String,
                    owner: String,
                    title: String,
                    start: LocalDateTime,
                    end: LocalDateTime
                  )