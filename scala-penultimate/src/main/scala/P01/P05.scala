package P01 {

  object P05 {

    def penultimate[A](list: List[A]): A = {
      iterate(list)
    }

    private def iterate[A](list: List[A]): A = {
      list match {
        case first :: _ :: Nil => {
          first
        }
        case _ :: tail => {
          iterate(tail)
        }
        case _ => {
          throw new NoSuchElementException("List too short")
        }
      }
    }
  }
}