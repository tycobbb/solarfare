package dev.wizrad.solarfare.support.extensions

import com.badlogic.gdx.math.Vector2

// MARK: Constructors
fun Vector2(width: Int, height: Int): Vector2 {
  return Vector2(width.toFloat(), height.toFloat())
}

// MARK: Operations
fun Vector2.minv(): Vector2 {
  x = 1.0f / x
  y = 1.0f / y
  return this
}

fun Vector2.mabs(): Vector2 {
  x = Math.abs(x)
  y = Math.abs(y)
  return this
}

fun Vector2.update(x: Float = this.x, y: Float = this.y): Vector2 {
  this.x = x
  this.y = y
  return this
}

// MARK: Reflection
data class Reflection(
  val x: Float? = null,
  val y: Float? = null) {

  constructor(axes: Array<out Axis>): this(
    x = if(axes.contains(Axis.X)) 1.0f else null,
    y = if(axes.contains(Axis.Y)) 1.0f else null)

  enum class Axis(val bit: Int) {
    X(0x01), Y(0x10)
  }
}

fun Vector2.mreflect(reflection: Reflection): Vector2 {
  if(reflection.x != null) {
    x = reflection.x - x
  }

  if(reflection.y != null) {
    y = reflection.y - y
  }

  return this
}
