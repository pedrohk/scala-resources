package pedrohk.cache.config

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CacheConfigurationTest
  extends AnyFlatSpec
    with Matchers {

  "CacheConfiguration" should "create cache manager" in {

    val configuration =
      new CacheConfiguration

    val manager =
      configuration.cacheManager()

    manager should not be null
  }
}