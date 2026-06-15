package P01 {

  object P08 {

    def last[A](list: List[A]): A = {
      def visit(items: List[A]): A = {
        items match {
          case Nil => {
            throw new NoSuchElementException("Empty list")
          }
          case value :: Nil => {
            value
          }
          case _ :: tail => {
            visit(tail)
          }
        }
      }

      visit(list)
    }
  }
}