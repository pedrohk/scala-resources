package P01 {

  object P01 {

    def penultimate[A](list: List[A]): A = {
      list match {
        case Nil => {
          throw new NoSuchElementException("List too short")
        }
        case _ :: Nil => {
          throw new NoSuchElementException("List too short")
        }
        case value :: _ :: Nil => {
          value
        }
        case _ :: tail => {
          penultimate(tail)
        }
      }
    }
  }
}