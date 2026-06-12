package P01 {

  object P05 {

    def last[A](list: List[A]): A = {
      traverse(list, None)
    }

    private def traverse[A](list: List[A], current: Option[A]): A = {
      list match {
        case Nil => {
          current match {
            case Some(value) => {
              value
            }
            case None => {
              throw new NoSuchElementException("Empty list")
            }
          }
        }
        case head :: tail => {
          traverse(tail, Some(head))
        }
      }
    }
  }
}