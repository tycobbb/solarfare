package dev.wizrad.solarfare.game.renderer.support

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import dev.wizrad.solarfare.support.geometry.Point
import dev.wizrad.solarfare.support.geometry.Size

// MARK: Domain
fun Size.toRectangle(): Rectangle {
  return Rectangle(0.0f, 0.0f, width.toFloat(), height.toFloat())
}

fun Point.toRectangle(width: Float = 2.0f, height: Float = 2.0f): Rectangle {
  return Rectangle(
    x.toFloat() - width  / 2,
    y.toFloat() + height / 2,
    width, height
  )
}
