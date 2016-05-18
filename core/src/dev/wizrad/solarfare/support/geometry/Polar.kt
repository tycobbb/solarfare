package dev.wizrad.solarfare.support.geometry

import dev.wizrad.solarfare.support.fmt

data class Polar(
  val radial: Double = 0.0,
  val angle:  Double = 0.0) {

  fun toPoint(): Point {
    return Point(
      x = radial * Math.cos(angle),
      y = radial * Math.sin(angle)
    )
  }

  // MARK: Debugging
  override fun toString(): String {
    return "Polar(rad=${radial.fmt()}, angle=${angle.fmt()}"
  }
}
