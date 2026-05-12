package stringimpl

import org.scalatest.funsuite.AnyFunSuite

class MyStringSpec extends AnyFunSuite {

  test("toArrayValue returns correct array") {
    val value = new MyString("scala")

    assert(value.toArrayValue.sameElements(Array('s', 'c', 'a', 'l', 'a')))
  }

  test("foreach iterates correctly") {
    val value = new MyString("abc")

    val builder = new StringBuilder()

    value.foreach(builder.append)

    assert(builder.toString() == "abc")
  }

  test("reverseString reverses correctly") {
    val value = new MyString("scala")

    assert(value.reverseString.toString == "alacs")
  }

  test("iterator iterates correctly") {
    val value = new MyString("xyz")

    val collected = value.iterator.toList

    assert(collected == List('x', 'y', 'z'))
  }

  test("lengthValue returns correct size") {
    val value = new MyString("hello")

    assert(value.lengthValue == 5)
  }

  test("charAt returns correct char") {
    val value = new MyString("scala")

    assert(value.charAt(2) == 'a')
  }

  test("charAt throws for invalid index") {
    val value = new MyString("scala")

    assertThrows[IndexOutOfBoundsException] {
      value.charAt(100)
    }
  }

  test("equals works for equal values") {
    val left = new MyString("abc")
    val right = new MyString("abc")

    assert(left == right)
  }

  test("equals works for different values") {
    val left = new MyString("abc")
    val right = new MyString("xyz")

    assert(left != right)
  }

  test("isEmptyValue returns true for empty string") {
    val value = new MyString("")

    assert(value.isEmptyValue)
  }

  test("isEmptyValue returns false for non empty string") {
    val value = new MyString("scala")

    assert(!value.isEmptyValue)
  }

  test("replace changes characters") {
    val value = new MyString("banana")

    assert(value.replace('a', 'o').toString == "bonono")
  }

  test("substring extracts correctly") {
    val value = new MyString("abcdef")

    assert(value.substring(1, 4).toString == "bcd")
  }

  test("substring throws for invalid range") {
    val value = new MyString("abcdef")

    assertThrows[IllegalArgumentException] {
      value.substring(5, 2)
    }
  }

  test("trimValue removes spaces") {
    val value = new MyString("   scala   ")

    assert(value.trimValue.toString == "scala")
  }

  test("trimValue works with only spaces") {
    val value = new MyString("     ")

    assert(value.trimValue.toString == "")
  }

  test("toJson escapes correctly") {
    val value = new MyString("hello \"scala\"")

    assert(value.toJson == "\"hello \\\"scala\\\"\"")
  }

  test("indexOf finds character") {
    val value = new MyString("scala")

    assert(value.indexOf('a') == 2)
  }

  test("indexOf returns minus one when not found") {
    val value = new MyString("scala")

    assert(value.indexOf('z') == -1)
  }

  test("hashCode is consistent") {
    val value = new MyString("scala")

    val first = value.hashCode()
    val second = value.hashCode()

    assert(first == second)
  }

  test("toString returns original value") {
    val value = new MyString("scala")

    assert(value.toString == "scala")
  }

  test("reverseString of empty string works") {
    val value = new MyString("")

    assert(value.reverseString.toString == "")
  }

  test("replace with absent char keeps string") {
    val value = new MyString("scala")

    assert(value.replace('z', 'x').toString == "scala")
  }

  test("multiple operations together work correctly") {
    val value = new MyString("  scala  ")

    val result =
      value
        .trimValue
        .replace('a', 'o')
        .reverseString

    assert(result.toString == "olocs")
  }

  test("iterator hasNext behaves correctly") {
    val value = new MyString("ab")

    val iterator = value.iterator

    assert(iterator.hasNext)
    iterator.next()

    assert(iterator.hasNext)
    iterator.next()

    assert(!iterator.hasNext)
  }

  test("knownSize returns correct value") {
    val value = new MyString("scala")

    assert(value.knownSize == 5)
  }
}