package dev.wizrad.solarfare.support.geometry

import dev.wizrad.solarfare.support.extensions.rand

data class Size(
  val width:  Double,
  val height: Double) {

  constructor(dimension: Double): this(dimension, dimension)

  // MARK: Operators
  operator fun times(other: Double): Size {
    return Size(
      width  = width  * other,
      height = height * other)
  }

  fun sample(): Point {
    return Point(
      x = rand.upto(width)  - width  / 2,
      y = rand.upto(height) - height / 2)
  }

  companion object {
    val zero = Size(0.0, 0.0)
  }
}
