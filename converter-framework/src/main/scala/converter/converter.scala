package converter

trait Converter[A, B] {

  def convert(value: A): B
}