package P01 {

  object P07 {

    def last[A](list: List[A]): A = {
      step(list)
    }

    private def step[A](list: List[A]): A = {
      list match {
        case Nil => {
          throw new NoSuchElementException("Empty list")
        }
        case value :: Nil => {
          value
        }
        case _ :: tail => {
          step(tail)
        }
      }
    }
  }
}