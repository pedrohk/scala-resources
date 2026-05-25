package pedrohk.service

import org.mockito.Mockito.{times, verify, when}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar
import pedrohk.model.{Profile, ProfileResponse}

class ProfileServiceTest extends AnyFlatSpec with Matchers with MockitoSugar {

  "ProfileService" should "map a gateway response into a domain profile" in {
    val gateway = mock[ProfileGateway]

    when(gateway.fetchProfile(31L)).thenReturn(
      ProfileResponse(31L, "Pedro Henrique", "Reactive APIs", true)
    )

    val service = new ProfileService(gateway)

    val result = service.retrieveProfile(31L)

    result shouldBe Profile(
      31L,
      "Pedro Henrique",
      "Reactive APIs",
      true
    )

    verify(gateway, times(1)).fetchProfile(31L)
  }

  it should "preserve disabled profiles" in {
    val gateway = mock[ProfileGateway]

    when(gateway.fetchProfile(88L)).thenReturn(
      ProfileResponse(88L, "Lia Martins", "Infrastructure Security", false)
    )

    val service = new ProfileService(gateway)

    val result = service.retrieveProfile(88L)

    result.active shouldBe false
    result.owner shouldBe "Lia Martins"
    result.expertise shouldBe "Infrastructure Security"
  }
}