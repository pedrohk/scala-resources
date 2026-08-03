package P10

object Main {

  def main(args: Array[String]): Unit = {
    val example: List[Symbol] =
      List(
        Symbol("a"), Symbol("a"), Symbol("a"), Symbol("a"),
        Symbol("b"),
        Symbol("c"), Symbol("c"),
        Symbol("a"), Symbol("a"),
        Symbol("d"),
        Symbol("e"), Symbol("e"), Symbol("e"), Symbol("e")
      )

    println(s"Input: $example")
    println("-" * 50)
    println(s"P01 -> ${new P01().encode(example)}")
    println(s"P02 -> ${new P02().encode(example)}")
    println(s"P03 -> ${new P03().encode(example)}")
    println(s"P04 -> ${new P04().encode(example)}")
    println(s"P05 -> ${new P05().encode(example)}")
    println(s"P06 -> ${new P06().encode(example)}")
    println(s"P07 -> ${new P07().encode(example)}")
    println(s"P08 -> ${new P08().encode(example)}")
    println(s"P09 -> ${new P09().encode(example)}")
    println(s"P10 -> ${new P10().encode(example)}")
  }
}
