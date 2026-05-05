package grocery

enum Status {
  case TODO, DONE
}

case class Item(
                 id: String,
                 name: String,
                 status: Status
               )