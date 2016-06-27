package dev.wizrad.solarfare.support.geometry

import dev.wizrad.solarfare.support.cosd
import dev.wizrad.solarfare.support.fmt
import dev.wizrad.solarfare.support.sind

data class Polar(
  val radial: Double = 0.0,
  val angle:  Double = 0.0) {

  fun toPoint(): Point {
    return Point(
      x = radial * cosd(angle),
      y = radial * sind(angle)
    )
  }

  // MARK: Debugging
  override fun toString(): String {
    return "Polar(rad=${radial.fmt()}, angle=${angle.fmt()}"
  }
}
