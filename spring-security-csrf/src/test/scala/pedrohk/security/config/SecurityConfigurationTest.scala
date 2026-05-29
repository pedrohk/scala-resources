package pedrohk.security.config

  import org.scalatest.flatspec.AnyFlatSpec
  import org.scalatest.matchers.should.Matchers

  class SecurityConfigurationTest
    extends AnyFlatSpec
      with Matchers {

    "passwordEncoder" should "return encoder instance" in {

      val securityConfiguration =
        new SecurityConfiguration

      val encoder =
        securityConfiguration.passwordEncoder()

      encoder should not be null
    }
  }