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
