package P01 {

  object P02 {

    def last[A](list: List[A]): A = {
      if (list.isEmpty) {
        throw new NoSuchElementException("Empty list")
      }

      def loop(current: List[A]): A = {
        current match {
          case value :: Nil => {
            value
          }
          case _ :: tail => {
            loop(tail)
          }
          case Nil => {
            throw new NoSuchElementException("Empty list")
          }
        }
      }

      loop(list)
    }
  }
}