package P01 {

  object P01 {

    def last[A](list: List[A]): A = {
      list match {
        case Nil => {
          throw new NoSuchElementException("Empty list")
        }
        case head :: Nil => {
          head
        }
        case _ :: tail => {
          last(tail)
        }
      }
    }
  }
}