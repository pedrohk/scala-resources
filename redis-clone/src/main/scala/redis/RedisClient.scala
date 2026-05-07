package redis

class RedisClient(server: RedisServer) {

  def set(key: String, value: String): Unit = {
    server.set(key, value)
  }

  def get(key: String): Option[String] = {
    server.get(key)
  }

  def remove(key: String): Boolean = {
    server.remove(key)
  }

  def append(key: String, value: String): String = {
    server.append(key, value)
  }

  def mapSet(mapKey: String, field: String, value: String): Unit = {
    server.mapSet(mapKey, field, value)
  }

  def mapGet(mapKey: String, field: String): Option[String] = {
    server.mapGet(mapKey, field)
  }

  def mapKeys(mapKey: String): List[String] = {
    server.mapKeys(mapKey)
  }

  def mapValues(mapKey: String): List[String] = {
    server.mapValues(mapKey)
  }
}