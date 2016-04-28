package dev.wizrad.solarfare.support.geometry

data class Polar(
  val radial: Double,
  val angle:  Double) {

  fun toPoint(): Point {
    return Point(
      x = radial * Math.cos(angle),
      y = radial * Math.sin(angle)
    )
  }
}
