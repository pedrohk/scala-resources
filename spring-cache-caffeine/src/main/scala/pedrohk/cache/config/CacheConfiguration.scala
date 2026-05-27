package pedrohk.cache.config

import java.util.concurrent.TimeUnit
import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CacheConfiguration {

  @Bean
  def cacheManager(): CacheManager = {

    val manager =
      new CaffeineCacheManager()

    manager.setCaffeine(
      Caffeine
        .newBuilder()
        .maximumSize(100)
        .expireAfterWrite(
          10,
          TimeUnit.MINUTES
        )
    )

    manager
  }
}