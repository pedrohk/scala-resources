package school

import scala.collection.mutable

class ClassOrganizer {

  private val sessions = mutable.ListBuffer.empty[ClassSession]

  def schedule(
                teacher: Teacher,
                studentGroup: StudentGroup,
                subject: Subject,
                classroom: Classroom,
                timeSlot: Int
              ): ClassSession = {

    require(timeSlot >= 0, "Invalid time slot")
    require(classroom.capacity >= studentGroup.size, "Classroom capacity exceeded")
    require(teacher.subjects.contains(subject), "Teacher cannot teach subject")

    val teacherConflict = sessions.exists { session =>
      session.teacher.id == teacher.id && session.timeSlot == timeSlot
    }

    if (teacherConflict) {
      throw new IllegalArgumentException("Teacher conflict")
    }

    val classroomConflict = sessions.exists { session =>
      session.classroom.id == classroom.id && session.timeSlot == timeSlot
    }

    if (classroomConflict) {
      throw new IllegalArgumentException("Classroom conflict")
    }

    val groupConflict = sessions.exists { session =>
      session.studentGroup.id == studentGroup.id && session.timeSlot == timeSlot
    }

    if (groupConflict) {
      throw new IllegalArgumentException("Student group conflict")
    }

    val session = ClassSession(
      teacher,
      studentGroup,
      subject,
      classroom,
      timeSlot
    )

    sessions += session

    session
  }

  def remove(session: ClassSession): Boolean = {
    val sizeBefore = sessions.size
    sessions -= session
    sizeBefore != sessions.size
  }

  def allSessions(): List[ClassSession] = {
    sessions.toList.sortBy(_.timeSlot)
  }

  def teacherSchedule(teacherId: String): List[ClassSession] = {
    sessions
      .filter(_.teacher.id == teacherId)
      .toList
      .sortBy(_.timeSlot)
  }

  def classroomSchedule(classroomId: String): List[ClassSession] = {
    sessions
      .filter(_.classroom.id == classroomId)
      .toList
      .sortBy(_.timeSlot)
  }

  def optimizeFreeSlots(totalSlots: Int): Int = {
    val used = sessions.map(_.timeSlot).distinct.size
    totalSlots - used
  }

  def totalSessions(): Int = {
    sessions.size
  }
}