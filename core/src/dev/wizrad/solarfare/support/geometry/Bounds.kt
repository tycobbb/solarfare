package dev.wizrad.solarfare.support.geometry

import dev.wizrad.solarfare.support.absd

data class Bounds(
  val minimum: Point,
  val maximum: Point) {

  fun size(): Size {
    return Size(
      width  = maximum.x - minimum.x,
      height = maximum.y - minimum.y)
  }

  fun offset(): Point {
    return Point(
      x = (absd(minimum.x) - absd(maximum.x)) / 2.0,
      y = (absd(minimum.y) - absd(maximum.y)) / 2.0)
  }
}
