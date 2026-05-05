package guitar

enum GuitarType {
  case ELECTRIC, ACOUSTIC, BASS
}

case class Specs(
                  wood: String,
                  pickups: Int,
                  hasTremolo: Boolean
                )

case class Model(
                  name: String,
                  guitarType: GuitarType
                )

case class Guitar(
                   id: String,
                   model: Model,
                   specs: Specs,
                   os: String
                 )