package converter

case class UserDTO(
                    id: String,
                    fullName: String,
                    age: Int,
                    address: AddressDTO
                  )