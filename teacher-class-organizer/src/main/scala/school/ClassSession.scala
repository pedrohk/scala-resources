package school

case class ClassSession(
                         teacher: Teacher,
                         studentGroup: StudentGroup,
                         subject: Subject,
                         classroom: Classroom,
                         timeSlot: Int
                       )