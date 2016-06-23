package dev.wizrad.solarfare.support.geometry

data class Bounds(
  val minimum: Point,
  val maximum: Point) {

  fun size(): Size {
    return Size(
      width  = maximum.x - minimum.x,
      height = maximum.y - minimum.y)
  }
}
