package converter

class UserConverter(
                     addressConverter: Converter[Address, AddressDTO]
                   ) extends Converter[User, UserDTO] {

  override def convert(value: User): UserDTO = {
    UserDTO(
      id = value.id,
      fullName = s"${value.firstName} ${value.lastName}",
      age = value.age,
      address = addressConverter.convert(value.address)
    )
  }
}