package redis

sealed trait RedisValue

case class StringValue(value: String) extends RedisValue

case class MapValue(value: Map[String, String]) extends RedisValue