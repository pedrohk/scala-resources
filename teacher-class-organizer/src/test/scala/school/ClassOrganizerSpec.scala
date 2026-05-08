package school

import org.scalatest.funsuite.AnyFunSuite

class ClassOrganizerSpec extends AnyFunSuite {

  def createTeacher(): Teacher = {
    Teacher(
      "t1",
      "John",
      Set(Subject.MATH, Subject.SCIENCE)
    )
  }

  def createGroup(): StudentGroup = {
    StudentGroup("g1", 20)
  }

  def createClassroom(): Classroom = {
    Classroom("c1", 30)
  }

  test("schedule class successfully") {
    val organizer = new ClassOrganizer

    val session = organizer.schedule(
      createTeacher(),
      createGroup(),
      Subject.MATH,
      createClassroom(),
      1
    )

    assert(session.subject == Subject.MATH)
    assert(organizer.totalSessions() == 1)
  }

  test("teacher conflict detection") {
    val organizer = new ClassOrganizer

    val teacher = createTeacher()

    organizer.schedule(
      teacher,
      StudentGroup("g1", 10),
      Subject.MATH,
      Classroom("c1", 20),
      1
    )

    assertThrows[IllegalArgumentException] {
      organizer.schedule(
        teacher,
        StudentGroup("g2", 10),
        Subject.SCIENCE,
        Classroom("c2", 20),
        1
      )
    }
  }

  test("classroom conflict detection") {
    val organizer = new ClassOrganizer

    val room = createClassroom()

    organizer.schedule(
      createTeacher(),
      StudentGroup("g1", 10),
      Subject.MATH,
      room,
      1
    )

    assertThrows[IllegalArgumentException] {
      organizer.schedule(
        Teacher("t2", "Mary", Set(Subject.HISTORY)),
        StudentGroup("g2", 10),
        Subject.HISTORY,
        room,
        1
      )
    }
  }

  test("student group conflict detection") {
    val organizer = new ClassOrganizer

    val group = createGroup()

    organizer.schedule(
      createTeacher(),
      group,
      Subject.MATH,
      Classroom("c1", 30),
      1
    )

    assertThrows[IllegalArgumentException] {
      organizer.schedule(
        Teacher("t2", "Mary", Set(Subject.HISTORY)),
        group,
        Subject.HISTORY,
        Classroom("c2", 30),
        1
      )
    }
  }

  test("teacher must support subject") {
    val organizer = new ClassOrganizer

    assertThrows[IllegalArgumentException] {
      organizer.schedule(
        createTeacher(),
        createGroup(),
        Subject.HISTORY,
        createClassroom(),
        1
      )
    }
  }

  test("classroom capacity validation") {
    val organizer = new ClassOrganizer

    assertThrows[IllegalArgumentException] {
      organizer.schedule(
        createTeacher(),
        StudentGroup("g1", 100),
        Subject.MATH,
        Classroom("c1", 20),
        1
      )
    }
  }

  test("remove session works") {
    val organizer = new ClassOrganizer

    val session = organizer.schedule(
      createTeacher(),
      createGroup(),
      Subject.MATH,
      createClassroom(),
      1
    )

    val removed = organizer.remove(session)

    assert(removed)
    assert(organizer.totalSessions() == 0)
  }

  test("remove missing session returns false") {
    val organizer = new ClassOrganizer

    val fake = ClassSession(
      createTeacher(),
      createGroup(),
      Subject.MATH,
      createClassroom(),
      1
    )

    assert(!organizer.remove(fake))
  }

  test("teacher schedule ordered") {
    val organizer = new ClassOrganizer

    val teacher = createTeacher()

    organizer.schedule(
      teacher,
      StudentGroup("g1", 10),
      Subject.MATH,
      Classroom("c1", 20),
      3
    )

    organizer.schedule(
      teacher,
      StudentGroup("g2", 10),
      Subject.SCIENCE,
      Classroom("c2", 20),
      1
    )

    val schedule = organizer.teacherSchedule("t1")

    assert(schedule.head.timeSlot == 1)
    assert(schedule.last.timeSlot == 3)
  }

  test("classroom schedule ordered") {
    val organizer = new ClassOrganizer

    val room = createClassroom()

    organizer.schedule(
      createTeacher(),
      StudentGroup("g1", 10),
      Subject.MATH,
      room,
      4
    )

    organizer.schedule(
      Teacher("t2", "Mary", Set(Subject.HISTORY)),
      StudentGroup("g2", 10),
      Subject.HISTORY,
      room,
      1
    )

    val schedule = organizer.classroomSchedule("c1")

    assert(schedule.head.timeSlot == 1)
    assert(schedule.last.timeSlot == 4)
  }

  test("optimize free slots") {
    val organizer = new ClassOrganizer

    organizer.schedule(
      createTeacher(),
      createGroup(),
      Subject.MATH,
      createClassroom(),
      1
    )

    organizer.schedule(
      Teacher("t2", "Mary", Set(Subject.HISTORY)),
      StudentGroup("g2", 10),
      Subject.HISTORY,
      Classroom("c2", 30),
      2
    )

    val free = organizer.optimizeFreeSlots(10)

    assert(free == 8)
  }

  test("high volume scheduling") {
    val organizer = new ClassOrganizer

    (1 to 100).foreach { i =>
      organizer.schedule(
        Teacher(
          s"t$i",
          s"Teacher-$i",
          Set(Subject.MATH)
        ),
        StudentGroup(s"g$i", 20),
        Subject.MATH,
        Classroom(s"c$i", 30),
        i
      )
    }

    assert(organizer.totalSessions() == 100)
  }

  test("negative slot validation") {
    val organizer = new ClassOrganizer

    assertThrows[IllegalArgumentException] {
      organizer.schedule(
        createTeacher(),
        createGroup(),
        Subject.MATH,
        createClassroom(),
        -1
      )
    }
  }

  test("all sessions ordered") {
    val organizer = new ClassOrganizer

    organizer.schedule(
      createTeacher(),
      StudentGroup("g1", 10),
      Subject.MATH,
      Classroom("c1", 20),
      5
    )

    organizer.schedule(
      Teacher("t2", "Mary", Set(Subject.HISTORY)),
      StudentGroup("g2", 10),
      Subject.HISTORY,
      Classroom("c2", 20),
      1
    )

    val sessions = organizer.allSessions()

    assert(sessions.head.timeSlot == 1)
    assert(sessions.last.timeSlot == 5)
  }

  test("consistency across multiple reads") {
    val organizer = new ClassOrganizer

    organizer.schedule(
      createTeacher(),
      createGroup(),
      Subject.MATH,
      createClassroom(),
      1
    )

    val first = organizer.allSessions()
    val second = organizer.allSessions()

    assert(first == second)
  }
}