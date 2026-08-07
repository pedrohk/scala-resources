package P11

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
    println(s"P01 -> ${new P01().encodeModified(example)}")
    println(s"P02 -> ${new P02().encodeModified(example)}")
    println(s"P03 -> ${new P03().encodeModified(example)}")
    println(s"P04 -> ${new P04().encodeModified(example)}")
    println(s"P05 -> ${new P05().encodeModified(example)}")
    println(s"P06 -> ${new P06().encodeModified(example)}")
    println(s"P07 -> ${new P07().encodeModified(example)}")
    println(s"P08 -> ${new P08().encodeModified(example)}")
    println(s"P09 -> ${new P09().encodeModified(example)}")
    println(s"P10 -> ${new P10().encodeModified(example)}")
  }
}
