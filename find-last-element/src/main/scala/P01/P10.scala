package P01 {

  object P10 {

    def last[A](list: List[A]): A = {
      fetch(list)
    }

    private def fetch[A](list: List[A]): A = {
      list match {
        case Nil => {
          throw new NoSuchElementException("Empty list")
        }
        case value :: Nil => {
          value
        }
        case _ :: tail => {
          fetch(tail)
        }
      }
    }
  }
}