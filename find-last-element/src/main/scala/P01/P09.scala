package P01 {

  object P09 {

    def last[A](list: List[A]): A = {
      evaluate(list)
    }

    private def evaluate[A](list: List[A]): A = {
      list match {
        case Nil => {
          throw new NoSuchElementException("Empty list")
        }
        case value :: Nil => {
          value
        }
        case _ :: tail => {
          evaluate(tail)
        }
      }
    }
  }
}