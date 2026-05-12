package stringimpl

final class MyString(private val value: Array[Char]) extends Iterable[Char] {

  def this(value: String) = {
    this(value.toCharArray)
  }

  def toArrayValue: Array[Char] = {
    value.clone()
  }

  override def foreach[U](f: Char => U): Unit = {
    var index = 0

    while (index < value.length) {
      f(value(index))
      index += 1
    }
  }

  def reverseString: MyString = {
    val reversed = new Array[Char](value.length)

    var left = 0
    var right = value.length - 1

    while (right >= 0) {
      reversed(left) = value(right)
      left += 1
      right -= 1
    }

    new MyString(reversed)
  }

  override def iterator: Iterator[Char] = {
    new Iterator[Char] {

      private var index = 0

      override def hasNext: Boolean = {
        index < value.length
      }

      override def next(): Char = {
        val current = value(index)
        index += 1
        current
      }
    }
  }

  override def knownSize: Int = {
    value.length
  }

  def charAt(index: Int): Char = {
    if (index < 0 || index >= value.length) {
      throw new IndexOutOfBoundsException(s"Invalid index: $index")
    }

    value(index)
  }

  override def equals(other: Any): Boolean = {
    other match {
      case that: MyString =>
        if (this.lengthValue != that.lengthValue) {
          false
        } else {
          var index = 0
          var equal = true

          while (index < value.length && equal) {
            if (value(index) != that.value(index)) {
              equal = false
            }

            index += 1
          }

          equal
        }

      case _ =>
        false
    }
  }

  def isEmptyValue: Boolean = {
    value.isEmpty
  }

  def replace(oldChar: Char, newChar: Char): MyString = {
    val replaced = value.clone()

    var index = 0

    while (index < replaced.length) {
      if (replaced(index) == oldChar) {
        replaced(index) = newChar
      }

      index += 1
    }

    new MyString(replaced)
  }

  def substring(begin: Int, end: Int): MyString = {
    if (begin < 0 || end > value.length || begin > end) {
      throw new IllegalArgumentException("Invalid substring range")
    }

    val result = new Array[Char](end - begin)

    var sourceIndex = begin
    var targetIndex = 0

    while (sourceIndex < end) {
      result(targetIndex) = value(sourceIndex)

      sourceIndex += 1
      targetIndex += 1
    }

    new MyString(result)
  }

  def trimValue: MyString = {
    var start = 0
    var end = value.length - 1

    while (start <= end && value(start).isWhitespace) {
      start += 1
    }

    while (end >= start && value(end).isWhitespace) {
      end -= 1
    }

    if (start > end) {
      new MyString("")
    } else {
      substring(start, end + 1)
    }
  }

  def toJson: String = {
    "\"" + this.toString
      .replace("\\", "\\\\")
      .replace("\"", "\\\"") + "\""
  }

  def indexOf(target: Char): Int = {
    var index = 0

    while (index < value.length) {
      if (value(index) == target) {
        return index
      }

      index += 1
    }

    -1
  }

  override def hashCode(): Int = {
    var result = 17

    var index = 0

    while (index < value.length) {
      result = 31 * result + value(index).toInt
      index += 1
    }

    result
  }

  def lengthValue: Int = {
    value.length
  }

  override def toString: String = {
    value.mkString
  }
}