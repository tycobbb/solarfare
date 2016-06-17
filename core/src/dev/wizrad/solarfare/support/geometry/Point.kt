package dev.wizrad.solarfare.support.geometry

import dev.wizrad.solarfare.support.extensions.rand
import dev.wizrad.solarfare.support.fmt

data class Point(
  val x: Double = 0.0,
  val y: Double = 0.0) {

  // MARK: Operations
  fun distanceTo(other: Point): Double {
    return Math.hypot(x - other.x, y - other.y)
  }

  fun magnitude(): Double {
    return Math.hypot(x, y)
  }

  fun isZero(): Boolean {
    return x == 0.0 && y == 0.0
  }

  // MARK: Operators
  operator fun plus(other: Point): Point {
    return Point(
      x = x + other.x,
      y = y + other.y)
  }

  operator fun minus(other: Point): Point {
    return Point(
      x = x - other.x,
      y = y - other.y)
  }

  operator fun times(scale: Double): Point {
    return Point(
      x = x * scale,
      y = y * scale)
  }

  override fun equals(other: Any?): Boolean {
    return (other as? Point).let { x == it?.x && y == it?.y }
  }

  // MARK: Companion
  companion object {
    val zero = Point(0.0, 0.0)
    val min  = Point(Double.MIN_VALUE, Double.MIN_VALUE)
    val max  = Point(Double.MAX_VALUE, Double.MAX_VALUE)

    fun random(bound: Double): Point {
      return random(bound, bound)
    }

    fun random(range: ClosedRange<Double>): Point {
      return random(
        x = rand.between(range),
        y = rand.between(range)
      )
    }

    fun random(bound: Point): Point {
      return random(bound.x, bound.y)
    }

    fun random(x: Double, y: Double): Point {
      return Point(
        x = rand.upto(x),
        y = rand.upto(y))
    }
  }

  override fun toString(): String {
    return "Point(x=${x.fmt()}, y=${y.fmt()})"
  }
}