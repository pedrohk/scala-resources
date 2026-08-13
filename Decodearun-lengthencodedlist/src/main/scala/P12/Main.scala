package P12

object Main {

  def main(args: Array[String]): Unit = {
    val example: List[(Int, Symbol)] =
      List(
        (4, Symbol("a")),
        (1, Symbol("b")),
        (2, Symbol("c")),
        (2, Symbol("a")),
        (1, Symbol("d")),
        (4, Symbol("e"))
      )

    println(s"Input: $example")
    println("-" * 50)
    println(s"P01 -> ${new P01().decode(example)}")
    println(s"P02 -> ${new P02().decode(example)}")
    println(s"P03 -> ${new P03().decode(example)}")
    println(s"P04 -> ${new P04().decode(example)}")
    println(s"P05 -> ${new P05().decode(example)}")
    println(s"P06 -> ${new P06().decode(example)}")
    println(s"P07 -> ${new P07().decode(example)}")
    println(s"P08 -> ${new P08().decode(example)}")
    println(s"P09 -> ${new P09().decode(example)}")
    println(s"P10 -> ${new P10().decode(example)}")
  }
}
