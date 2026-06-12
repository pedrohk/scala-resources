package P01 {

  object P06 {

    def last[A](list: List[A]): A = {
      list match {
        case Nil => {
          throw new NoSuchElementException("Empty list")
        }
        case _ => {
          recurse(list)
        }
      }
    }

    private def recurse[A](remaining: List[A]): A = {
      remaining match {
        case value :: Nil => {
          value
        }
        case _ :: tail => {
          recurse(tail)
        }
      }
    }
  }
}