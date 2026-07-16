package P07

object Main {

  def main(args: Array[String]): Unit = {
    val example: List[Any] = List(List(1, 1), 2, List(3, List(5, 8)))

    val solvers: List[(String, List[Any] => List[Any])] = List(
      "P01" -> (new P01().flatten),
      "P02" -> (new P02().flatten),
      "P03" -> (new P03().flatten),
      "P04" -> (new P04().flatten),
      "P05" -> (new P05().flatten),
      "P06" -> (new P06().flatten),
      "P07" -> (new P07().flatten),
      "P08" -> (new P08().flatten),
      "P09" -> (new P09().flatten),
      "P10" -> (new P10().flatten)
    )

    println(s"Input: $example")
    println("-" * 50)
    solvers.foreach { case (name, solve) =>
      println(s"$name -> ${solve(example)}")
    }
  }
}
