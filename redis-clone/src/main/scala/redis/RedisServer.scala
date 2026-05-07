package redis

import scala.collection.concurrent.TrieMap

class RedisServer {

  private val stringStore = TrieMap.empty[String, String]

  private val mapStore = TrieMap.empty[String, TrieMap[String, String]]

  def set(key: String, value: String): Unit = {
    stringStore.put(key, value)
  }

  def get(key: String): Option[String] = {
    stringStore.get(key)
  }

  def remove(key: String): Boolean = {
    stringStore.remove(key).isDefined
  }

  def append(key: String, value: String): String = {
    val updated = stringStore.getOrElse(key, "") + value
    stringStore.put(key, updated)
    updated
  }

  def mapSet(mapKey: String, field: String, value: String): Unit = {
    val map = mapStore.getOrElseUpdate(mapKey, TrieMap.empty[String, String])
    map.put(field, value)
  }

  def mapGet(mapKey: String, field: String): Option[String] = {
    mapStore.get(mapKey).flatMap(_.get(field))
  }

  def mapKeys(mapKey: String): List[String] = {
    mapStore.get(mapKey).map(_.keys.toList.sorted).getOrElse(List.empty)
  }

  def mapValues(mapKey: String): List[String] = {
    mapStore.get(mapKey).map(_.values.toList.sorted).getOrElse(List.empty)
  }

  def stringSize: Int = {
    stringStore.size
  }

  def mapSize: Int = {
    mapStore.size
  }

  def clear(): Unit = {
    stringStore.clear()
    mapStore.clear()
  }
}