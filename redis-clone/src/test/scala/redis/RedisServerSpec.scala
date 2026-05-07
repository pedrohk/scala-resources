package redis

import org.scalatest.funsuite.AnyFunSuite

class RedisServerSpec extends AnyFunSuite {

  def createClient(): RedisClient = {
    new RedisClient(new RedisServer)
  }

  test("set and get string value") {
    val client = createClient()

    client.set("name", "john")

    assert(client.get("name").contains("john"))
  }

  test("get unknown key returns none") {
    val client = createClient()

    assert(client.get("missing").isEmpty)
  }

  test("remove existing key") {
    val client = createClient()

    client.set("x", "1")

    val removed = client.remove("x")

    assert(removed)
    assert(client.get("x").isEmpty)
  }

  test("remove unknown key returns false") {
    val client = createClient()

    assert(!client.remove("unknown"))
  }

  test("append existing value") {
    val client = createClient()

    client.set("message", "hello")

    val result = client.append("message", "-world")

    assert(result == "hello-world")
    assert(client.get("message").contains("hello-world"))
  }

  test("append to missing key creates value") {
    val client = createClient()

    val result = client.append("new", "value")

    assert(result == "value")
    assert(client.get("new").contains("value"))
  }

  test("map set and get") {
    val client = createClient()

    client.mapSet("users", "1", "john")

    assert(client.mapGet("users", "1").contains("john"))
  }

  test("map get unknown field returns none") {
    val client = createClient()

    client.mapSet("users", "1", "john")

    assert(client.mapGet("users", "2").isEmpty)
  }

  test("map keys returns sorted keys") {
    val client = createClient()

    client.mapSet("users", "b", "mary")
    client.mapSet("users", "a", "john")

    val keys = client.mapKeys("users")

    assert(keys == List("a", "b"))
  }

  test("map values returns values") {
    val client = createClient()

    client.mapSet("users", "1", "john")
    client.mapSet("users", "2", "mary")

    val values = client.mapValues("users")

    assert(values.contains("john"))
    assert(values.contains("mary"))
    assert(values.size == 2)
  }

  test("empty map keys returns empty list") {
    val client = createClient()

    assert(client.mapKeys("missing").isEmpty)
  }

  test("empty map values returns empty list") {
    val client = createClient()

    assert(client.mapValues("missing").isEmpty)
  }

  test("overwrite string value") {
    val client = createClient()

    client.set("key", "a")
    client.set("key", "b")

    assert(client.get("key").contains("b"))
  }

  test("overwrite map field") {
    val client = createClient()

    client.mapSet("users", "1", "john")
    client.mapSet("users", "1", "mary")

    assert(client.mapGet("users", "1").contains("mary"))
  }

  test("large number of string operations") {
    val client = createClient()

    (1 to 1000).foreach { i =>
      client.set(s"key-$i", s"value-$i")
    }

    assert(client.get("key-1").contains("value-1"))
    assert(client.get("key-1000").contains("value-1000"))
  }

  test("large number of map operations") {
    val client = createClient()

    (1 to 500).foreach { i =>
      client.mapSet("data", s"field-$i", s"value-$i")
    }

    val keys = client.mapKeys("data")

    assert(keys.size == 500)
    assert(keys.contains("field-1"))
    assert(keys.contains("field-500"))
  }

  test("append multiple times") {
    val client = createClient()

    client.append("log", "a")
    client.append("log", "b")
    client.append("log", "c")

    assert(client.get("log").contains("abc"))
  }

  test("independent maps") {
    val client = createClient()

    client.mapSet("users", "1", "john")
    client.mapSet("admins", "1", "mary")

    assert(client.mapGet("users", "1").contains("john"))
    assert(client.mapGet("admins", "1").contains("mary"))
  }

  test("special characters support") {
    val client = createClient()

    client.set("emoji", "🔥")

    assert(client.get("emoji").contains("🔥"))
  }

  test("empty string values") {
    val client = createClient()

    client.set("empty", "")

    assert(client.get("empty").contains(""))
  }

  test("consistency across multiple reads") {
    val client = createClient()

    client.set("stable", "value")

    val first = client.get("stable")
    val second = client.get("stable")

    assert(first == second)
  }
}