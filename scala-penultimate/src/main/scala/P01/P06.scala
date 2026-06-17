package P01 {

  object P06 {

    def penultimate[A](list: List[A]): A = {
      move(list)
    }

    private def move[A](list: List[A]): A = {
      list match {
        case first :: _ :: Nil => {
          first
        }
        case _ :: tail => {
          move(tail)
        }
        case _ => {
          throw new NoSuchElementException("List too short")
        }
      }
    }
  }
}