package converter

import scala.collection.mutable

class ConverterRegistry {

  private val converters =
    mutable.Map[(Class[?], Class[?]), Converter[?, ?]]()

  def register[A, B](
                      source: Class[A],
                      target: Class[B],
                      converter: Converter[A, B]
                    ): Unit = {

    converters.put((source, target), converter)
  }

  def convert[A, B](
                     value: A,
                     source: Class[A],
                     target: Class[B]
                   ): B = {

    val converter = converters
      .getOrElse(
        (source, target),
        throw new IllegalArgumentException("Converter not found")
      )
      .asInstanceOf[Converter[A, B]]

    converter.convert(value)
  }

  def size(): Int = {
    converters.size
  }
}