package dev.wizrad.solarfare.game.components.controls

import com.badlogic.gdx.math.Vector2

data class Touch(
  val event: Event,
  val point: Vector2) {

  constructor(event: Event, x: Int, y: Int):
    this(event, Vector2(x.toFloat(), y.toFloat())) { }

  enum class Event { Began, Moved, Ended }
}
