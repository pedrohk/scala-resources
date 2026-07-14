package P07

class P06 {

  def flatten(list: List[Any]): List[Any] = {
    flattenList(list)
  }

  private def flattenList(list: List[Any]): List[Any] = {
    list match {
      case Nil    => Nil
      case h :: t => concat(flattenElement(h), flattenList(t))
    }
  }

  private def flattenElement(elem: Any): List[Any] = {
    elem match {
      case nested: List[Any] => flattenList(nested)
      case leaf               => leaf :: Nil
    }
  }

  private def concat(a: List[Any], b: List[Any]): List[Any] = {
    a match {
      case Nil       => b
      case h :: rest => h :: concat(rest, b)
    }
  }
}
