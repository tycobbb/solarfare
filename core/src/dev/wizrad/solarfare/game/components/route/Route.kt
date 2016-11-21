package dev.wizrad.solarfare.game.components.route

import com.badlogic.gdx.math.Vector2
import dev.wizrad.solarfare.game.components.CoordinateSpace.Companion.normal
import dev.wizrad.solarfare.game.components.CoordinateSpace.Companion.transform
import dev.wizrad.solarfare.game.components.CoordinateSpace.Companion.worldport
import dev.wizrad.solarfare.game.components.CoordinateSpace.Companion.world
import dev.wizrad.solarfare.game.components.controls.Touch
import dev.wizrad.solarfare.game.core.Targetable
import dev.wizrad.solarfare.support.unwrap

class Route(
  target: Targetable?) {

  // MARK: Properties
  private val _points = mutableListOf<Vector2>()
  private var _event  = Touch.Event.Began

  val points: List<Vector2> get() = _points.toList()
  val event:  Touch.Event   get() = _event

  init {
    target.unwrap {
      _points.add(transform(it.position, from = world, to = normal))
    }
  }

  // MARK: Mutation
  fun append(touch: Touch): Route {
    _event = touch.event

    val point = transform(touch.point, from = worldport, to = normal)
    if(_points.size == 0 || _points.last().dst(point) > threshold) {
      _points.add(point)
    }

    return this
  }

  // MARK: Constants
  companion object {
    val threshold = 0.001f
  }
}
