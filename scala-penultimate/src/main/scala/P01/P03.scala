package P01 {

  object P03 {

    def penultimate[A](list: List[A]): A = {

      @annotation.tailrec
      def loop(current: List[A]): A = {
        current match {
          case first :: _ :: Nil => {
            first
          }
          case _ :: tail => {
            loop(tail)
          }
          case _ => {
            throw new NoSuchElementException("List too short")
          }
        }
      }

      loop(list)
    }
  }
}