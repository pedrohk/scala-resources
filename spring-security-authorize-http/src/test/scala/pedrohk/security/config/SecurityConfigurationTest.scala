package pedrohk.security.config

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SecurityConfigurationTest
  extends AnyFlatSpec
    with Matchers {

  "passwordEncoder" should "return encoder instance" in {

    val configuration =
      new SecurityConfiguration

    val encoder =
      configuration.passwordEncoder()

    encoder should not be null
  }

  it should "encode passwords" in {

    val configuration =
      new SecurityConfiguration

    val encoder =
      configuration.passwordEncoder()

    encoder.encode(
      "pedro-secure"
    ) shouldBe "pedro-secure"
  }

  it should "match encoded password" in {

    val configuration =
      new SecurityConfiguration

    val encoder =
      configuration.passwordEncoder()

    encoder.matches(
      "lia-access",
      "lia-access"
    ) shouldBe true
  }

  it should "reject invalid password match" in {

    val configuration =
      new SecurityConfiguration

    val encoder =
      configuration.passwordEncoder()

    encoder.matches(
      "lia-access",
      "different"
    ) shouldBe false
  }
}