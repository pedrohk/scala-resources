package P01 {

  object P03 {

    def last[A](list: List[A]): A = {
      @annotation.tailrec
      def loop(current: List[A]): A = {
        current match {
          case Nil => {
            throw new NoSuchElementException("Empty list")
          }
          case value :: Nil => {
            value
          }
          case _ :: tail => {
            loop(tail)
          }
        }
      }

      loop(list)
    }
  }
}