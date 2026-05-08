package school

case class Teacher(
                    id: String,
                    name: String,
                    subjects: Set[Subject]
                  )