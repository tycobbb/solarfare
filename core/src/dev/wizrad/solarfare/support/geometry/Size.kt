package dev.wizrad.solarfare.support.geometry

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

  companion object {
    val zero = Size(0.0, 0.0)
  }
}
