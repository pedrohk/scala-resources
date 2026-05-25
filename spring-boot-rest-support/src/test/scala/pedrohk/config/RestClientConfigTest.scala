package pedrohk.config

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.springframework.web.client.RestTemplate

class RestClientConfigTest extends AnyFlatSpec with Matchers {

  "RestClientConfig" should "create a RestTemplate instance" in {
    val config = new RestClientConfig()

    val result = config.restTemplate()

    result shouldBe a[RestTemplate]
  }

  it should "create distinct RestTemplate instances" in {
    val config = new RestClientConfig()

    val first = config.restTemplate()
    val second = config.restTemplate()

    first should not be theSameInstanceAs(second)
  }
}