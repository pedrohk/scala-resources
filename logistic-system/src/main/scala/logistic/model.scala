package logistic

enum TransportType {
  case TRUCK, SHIP, RAIL
}

case class Dimensions(length: Double, width: Double, height: Double) {
  def volume: Double = {
    length * width * height
  }
}

case class Shipment(
                     weight: Double,
                     dimensions: Dimensions,
                     transportType: TransportType
                   )