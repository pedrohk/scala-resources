package P01 {

  object P04 {

    def last[A](list: List[A]): A = {
      list match {
        case Nil => {
          throw new NoSuchElementException("Empty list")
        }
        case _ => {
          extract(list)
        }
      }
    }

    private def extract[A](list: List[A]): A = {
      list match {
        case head :: Nil => {
          head
        }
        case _ :: tail => {
          extract(tail)
        }
      }
    }
  }
}