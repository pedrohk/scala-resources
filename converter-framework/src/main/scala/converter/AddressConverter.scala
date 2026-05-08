package converter

class AddressConverter extends Converter[Address, AddressDTO] {

  override def convert(value: Address): AddressDTO = {
    AddressDTO(
      s"${value.street}, ${value.city}, ${value.country}, ${value.zipCode}"
    )
  }
}