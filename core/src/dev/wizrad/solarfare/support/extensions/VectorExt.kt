package dev.wizrad.solarfare.support.extensions

import com.badlogic.gdx.math.Vector2

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
