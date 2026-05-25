package pedrohk.service


import org.mockito.ArgumentMatchers.{any, eq as eqTo}
import org.mockito.Mockito.{mock, times, verify, when}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar
import org.springframework.http.{HttpMethod, HttpStatus, ResponseEntity}
import org.springframework.web.client.RestOperations
import pedrohk.model.ProfileResponse

class ProfileGatewayTest extends AnyFlatSpec with Matchers with MockitoSugar {

  "ProfileGateway" should "fetch a profile using RestTemplate" in {
    val restTemplate = mock[RestOperations]
    val responseBody = ProfileResponse(7L, "Pedro Henrique", "Distributed Systems", true)
    val responseEntity = new ResponseEntity[ProfileResponse](responseBody, HttpStatus.OK)

    when(
      restTemplate.exchange(
        eqTo("https://profiles.internal/api/profiles/7"),
        eqTo(HttpMethod.GET),
        any(),
        eqTo(classOf[ProfileResponse])
      )
    ).thenReturn(responseEntity)

    val gateway = new ProfileGateway(restTemplate, "https://profiles.internal")

    val result = gateway.fetchProfile(7L)

    result shouldBe responseBody

    verify(restTemplate, times(1)).exchange(
      eqTo("https://profiles.internal/api/profiles/7"),
      eqTo(HttpMethod.GET),
      any(),
      eqTo(classOf[ProfileResponse])
    )
  }

  it should "support different profile identifiers" in {
    val restTemplate = mock[RestOperations]
    val responseBody = ProfileResponse(19L, "Lia Martins", "Cloud Architecture", false)
    val responseEntity = new ResponseEntity[ProfileResponse](responseBody, HttpStatus.OK)

    when(
      restTemplate.exchange(
        eqTo("https://gateway.example/api/profiles/19"),
        eqTo(HttpMethod.GET),
        any(),
        eqTo(classOf[ProfileResponse])
      )
    ).thenReturn(responseEntity)

    val gateway = new ProfileGateway(restTemplate, "https://gateway.example")

    val result = gateway.fetchProfile(19L)

    result.identifier shouldBe 19L
    result.displayName shouldBe "Lia Martins"
    result.specialty shouldBe "Cloud Architecture"
    result.enabled shouldBe false
  }
}