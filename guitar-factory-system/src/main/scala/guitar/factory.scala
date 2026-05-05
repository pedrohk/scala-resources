package guitar

import java.util.UUID

class GuitarFactory {

  def build(model: Model, specs: Specs, os: String): Guitar = {
    val id = UUID.randomUUID().toString
    Guitar(id, model, specs, os)
  }
}