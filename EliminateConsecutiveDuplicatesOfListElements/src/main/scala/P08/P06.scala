package P08

class P06 {

  def compress[A](list: List[A]): List[A] = {
    compressList(list)
  }

  private def compressList[A](list: List[A]): List[A] = {
    list match {
      case Nil    => Nil
      case h :: t => h :: skipDuplicatesOf(h, t)
    }
  }

  private def skipDuplicatesOf[A](value: A, list: List[A]): List[A] = {
    list match {
      case h :: t if h == value => skipDuplicatesOf(value, t)
      case other                => compressList(other)
    }
  }
}
