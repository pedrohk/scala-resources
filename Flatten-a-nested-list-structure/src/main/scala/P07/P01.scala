package P07

class P01 {

  def flatten(list: List[Any]): List[Any] = {
    list match {
      case Nil =>
        Nil
      case (head: List[Any]) :: tail =>
        append(flatten(head), flatten(tail))
      case head :: tail =>
        head :: flatten(tail)
    }
  }
  
  private def append(a: List[Any], b: List[Any]): List[Any] = {
    a match {
      case Nil       => b
      case h :: rest => h :: append(rest, b)
    }
  }
}
