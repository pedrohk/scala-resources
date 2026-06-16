package P01 {

  object P04 {

    def penultimate[A](list: List[A]): A = {
      search(list)
    }

    private def search[A](list: List[A]): A = {
      list match {
        case first :: _ :: Nil => {
          first
        }
        case _ :: tail => {
          search(tail)
        }
        case _ => {
          throw new NoSuchElementException("List too short")
        }
      }
    }
  }
}