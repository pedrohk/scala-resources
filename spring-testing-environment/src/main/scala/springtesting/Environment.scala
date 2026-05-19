package springtesting

import java.util.concurrent.ConcurrentHashMap

class Environment {
  private val properties: ConcurrentHashMap[String, String] = new ConcurrentHashMap[String, String]()
  private val activeProfiles: java.util.Set[String] = java.util.concurrent.ConcurrentHashMap.newKeySet[String]()

  def setProperty(key: String, value: String): Unit = {
    properties.put(key, value)
  }

  def getProperty(key: String): String = {
    properties.get(key)
  }

  def getProperty(key: String, defaultValue: String): String = {
    val value = properties.get(key)
    if (value == null) {
      defaultValue
    } else {
      value
    }
  }

  def addActiveProfile(profile: String): Unit = {
    activeProfiles.add(profile)
  }

  def acceptsProfiles(profile: String): Boolean = {
    activeProfiles.contains(profile)
  }
}
