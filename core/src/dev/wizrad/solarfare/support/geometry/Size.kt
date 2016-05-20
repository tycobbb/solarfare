package dev.wizrad.solarfare.support.geometry

data class Size(
  val width: Double,
  val height: Double) {

  constructor(dimension: Double): this(dimension, dimension)

  companion object {
    val zero = Size(0.0, 0.0)
  }
}
