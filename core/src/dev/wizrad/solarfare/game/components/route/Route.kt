package dev.wizrad.solarfare.game.components.route

import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.game.components.controls.Touch
import dev.wizrad.solarfare.support.extensions.append

class Route {
  // MARK: Properties
  private val _points = mutableListOf<Vector2>()
  private var _event  = Touch.Event.Began

  val points: List<Vector2> get() = _points.toList()
  val event:  Touch.Event   get() = _event

  // MARK: Mutation
  fun append(touch: Touch): Route {
    _event = touch.event

    if(_points.size == 0 || _points.last().dst(touch.point) > threshold) {
      _points.append(touch.point)
    }

    return this
  }

  // MARK: Constants
  companion object {
    val threshold = 0.1f
  }
}
